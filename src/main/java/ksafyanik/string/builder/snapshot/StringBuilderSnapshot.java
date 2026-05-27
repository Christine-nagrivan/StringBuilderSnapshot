package ksafyanik.string.builder.snapshot;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Objects;

public final class StringBuilderSnapshot {
    private byte[] value;
    private int count;
    private int capacity;
    private final Deque<Snapshot> snapshotHistory;

    StringBuilderSnapshot() {
        this.value = new byte[16];
        this.capacity = 16;
        this.count = 0;
        this.snapshotHistory = new ArrayDeque<>();
        snapshotHistory.add(createSnapshot());
    }

    StringBuilderSnapshot(int capacity) {
        this.value = new byte[capacity];
        this.capacity = capacity;
        this.count = 0;
        this.snapshotHistory = new ArrayDeque<>();
        snapshotHistory.add(createSnapshot());
    }

    StringBuilderSnapshot(String str) {
        this.count = str.length();
        this.capacity = (this.count < Integer.MAX_VALUE - 16)
                ? this.count + 16 : Integer.MAX_VALUE;
        this.value = new byte[capacity];
        byte[] bytes = str.getBytes();
        System.arraycopy(bytes, 0, this.value, 0, bytes.length);
        this.snapshotHistory = new ArrayDeque<>();
        snapshotHistory.add(createSnapshot());
    }

    public Snapshot createSnapshot() {
        return new Snapshot(value, capacity, count);
    }
    public void undo() {
        if (Objects.nonNull(snapshotHistory)) {
            Snapshot snapshot = this.snapshotHistory.pop();
            this.value = snapshot.getValue();
            this.capacity = snapshot.getCapacity();
            this.count = snapshot.getCount();
        }
    }

    public byte[] getValue() {
        return value;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getCount() {
        return count;
    }

    private class Snapshot {
        private final byte[] value;
        private final int count;
        private final int capacity;

        Snapshot(byte[] value, int capacity, int count) {
            this.value = value;
            this.capacity = capacity;
            this.count = count;
        }

        public byte[] getValue() {
            return value;
        }

        public int getCapacity() {
            return capacity;
        }

        public int getCount() {
            return count;
        }
    }
}

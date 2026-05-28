package ksafyanik.string.builder.snapshot;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Objects;

public final class StringBuilderSnapshot {
    private StringBuilder value;
    private final Deque<Snapshot> snapshotHistory;

    StringBuilderSnapshot() {
        this.value = new StringBuilder();
        this.snapshotHistory = new ArrayDeque<>();
        snapshotHistory.add(createSnapshot());
    }

    StringBuilderSnapshot(int capacity) {
        this.value = new StringBuilder(capacity);
        this.snapshotHistory = new ArrayDeque<>();
        snapshotHistory.add(createSnapshot());
    }

    StringBuilderSnapshot(String str) {
        this.value = new StringBuilder(str);
        this.snapshotHistory = new ArrayDeque<>();
        snapshotHistory.add(createSnapshot());
    }

    public StringBuilder getValue() {
        return value;
    }

    public StringBuilder append(String str) {
        snapshotHistory.add(createSnapshot());
        return this.value.append(str);
    }

    @Override
    public String toString() {
        return this.value.toString();
    }

    public Snapshot createSnapshot() {
        return new Snapshot(value);
    }

    public void undo() {
        if (Objects.nonNull(snapshotHistory)) {
            Snapshot snapshot = this.snapshotHistory.pop();
            this.value = new StringBuilder(snapshot.getValue());
        }
    }

    private class Snapshot {
        private final String value;

        Snapshot(StringBuilder value) {
            this.value = value.toString();
        }

        public String getValue() {
            return value;
        }
    }
}

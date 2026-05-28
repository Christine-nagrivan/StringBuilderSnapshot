package ksafyanik.string.builder.snapshot;


public class Application {

	public static void main(String[] args) {
		StringBuilderSnapshot stringBuilderSnapshotTest = new StringBuilderSnapshot("Тайная комната снова открыта!");
		System.out.println("Состояние StringBuilderSnapshot до изменения: " + stringBuilderSnapshotTest);
		stringBuilderSnapshotTest.append(" Враги наследника, трепещите!");
		System.out.println("Состояние StringBuilderSnapshot после изменения: " + stringBuilderSnapshotTest);
		stringBuilderSnapshotTest.undo();
		System.out.println("Состояние StringBuilderSnapshot после применения метода undo(): " + stringBuilderSnapshotTest);
	}

}

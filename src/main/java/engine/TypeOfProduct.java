package engine;

public enum TypeOfProduct {
    Fragile,
    Standard,
    Food,

    Batch,
    SpecialVolume;

    @Override
    public String toString() {
        return "TypeOfProduct{" +
                "name='" + this.name() + '\'' +
                '}';
    }
}

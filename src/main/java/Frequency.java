public enum Frequency {
    WEEK(1),
    TWO_WEEK(2),
    FOUR_WEEK(4),
    MONTH(4),
    QUARTER(13),
    YEAR(52);
    Frequency(int frequencyValue) {
        this.frequencyValue = frequencyValue;
    }
    int frequencyValue;
}

package vinay.com.simplecalcforunittesting.utils.intervalexample;

class IntervalOverlapDetector {
    public boolean isOverlap(Interval interval1, Interval interval2) {
        return interval1.getEnd() > interval2.getStart() && interval1.getStart() < interval2.getEnd();
    }
}

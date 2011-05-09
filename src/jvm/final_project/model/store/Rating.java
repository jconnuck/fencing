package final_project.model.store;

public class Rating implements Comparable<Rating> {
    public final String _rating, _year;
    
    public Rating(String rat) {
        _rating = rat.substring(0,1);
        _year = rat.substring(1);
    }

    public boolean equals(Object other) {
        if (other == null || !(other instanceof Rating))
            return false;
        Rating orat = (Rating) other;
        return _rating.equals(orat._rating)&&_year.equals(orat._year);
    }

    public int hashCode() {
        return _rating.hashCode()+_year.hashCode();
    }

    public int compareTo(Rating other) {
        if (equals(other))
            return 0;

        int ratingComparison = _rating.compareTo(other._rating);
        if (ratingComparison != 0)
            return ratingComparison;
        
        int yearComparison = _year.compareTo(other._year);
        if (yearComparison < 0)
            return 1;
        return -1;
    }
}
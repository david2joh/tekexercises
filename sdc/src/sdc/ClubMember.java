package sdc;

public class ClubMember {
    private String name;
    private String city;
    private String state;
    private String language;

    public ClubMember(String firstName, String city, String state, String language) {
        this.name = firstName;
        this.city = city;
        this.state = state;
        this.language = language;
    }

    @Override
    public String toString() {
        return
                "Member Name: " + name + "\n" +
                "Location: " + city +", " +
                state + "\n" +
                "Favorite Language: " + language + "\n";
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getLanguage() {
        return language;
    }

    public int compareTo(ClubMember that) {
        int result = 0;
        result = this.name.compareTo(that.getName());
        if (result != 0) return result;
        else {
            result = this.city.compareTo(that.getCity());
            if (result != 0) return result;
            else {
                result = this.state.compareTo(that.getState());
                if (result != 0) return result;
                else {
                    return (this.language.compareTo(that.getLanguage()));
                }
            }
        }
    }

} //class ClubMember

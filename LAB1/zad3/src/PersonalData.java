public class PersonalData {
    private String birthDate;
    private String gender;

    public PersonalData(String birthDate, String gender) {
        this.birthDate = birthDate;
        this.gender = gender;
    }

    public PersonalData() {}

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Birth date: ")
                .append(this.birthDate)
                .append(", gender: ")
                .append(this.gender);
        return sb.toString();
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthDate() {
        return this.birthDate;
    }

    public String getGender() {
        return this.gender;
    }
}

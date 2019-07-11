public class Jobs {
    //Variables
    private static int nextJobId = 1;
    private String jobId;
    private String jobTitle;

    //Constructor
    public Jobs(String jobTitle) {
        this.jobId = String.format("J%02d",nextJobId++);
        this.jobTitle = jobTitle;
    }

    //Getter
    public String getJobId() {
        return jobId;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    //Setter
    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    //Methods
    public String toString() {
        return "\nJob ID: " + jobId +
                "\nJob Title: " + jobTitle;
    }
}
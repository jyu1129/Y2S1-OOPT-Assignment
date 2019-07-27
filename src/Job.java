class Job {
    //Variables
    private static int nextJobId = 1;
    protected String jobId;
    protected String jobTitle;

    //Constructor
    public Job(String jobTitle) {
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

    @Override
    public String toString() {
        return "\nJob ID: " + jobId +
                "\nJob Title: " + jobTitle;
    }
}
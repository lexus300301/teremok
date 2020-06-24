package domain;


import dao.UserDao;

public class Worker {
    private int workerId;
    private int workerSalary;
    private String workerName;
    private String workerSurname;
    private String workerPhone;
    private UserDao dao;

    public Worker() {
    }

    public Worker(int workerId, int workerSalary, String workerEmail, String workerName, String workerSurname, String workerPhone) {
        this.workerId = workerId;
        this.workerSalary = workerSalary;
        this.workerName = workerName;
        this.workerSurname = workerSurname;
        this.workerPhone = workerPhone;
    }

    @Override
    public String toString() {
        return "Worker{" +
                "workerId=" + workerId +
                ", workerSalary=" + workerSalary +
                ", workerName='" + workerName + '\'' +
                ", workerSurname='" + workerSurname + '\'' +
                ", workerPhone='" + workerPhone + '\'' +
                '}';
    }

    public String getWorkerName() {
        return workerName;
    }

    public void setWorkerName(String workerName) {
        this.workerName = workerName;
    }

    public String getWorkerSurname() {
        return workerSurname;
    }

    public void setWorkerSurname(String workerSurname) {
        this.workerSurname = workerSurname;
    }

    public int getWorkerId() {
        return workerId;
    }

    public void setWorkerId(int workerId) {
        this.workerId = workerId;
    }


    public int getWorkerSalary() {
        return workerSalary;
    }

    public void setWorkerSalary(int workerSalary) {
        this.workerSalary = workerSalary;
    }

    public String getWorkerPhone() {
        return workerPhone;
    }

    public void setWorkerPhone(String workerPhone) {
        this.workerPhone = workerPhone;
    }


}

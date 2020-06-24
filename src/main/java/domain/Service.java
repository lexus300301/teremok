package domain;


public class Service {
    private int serviceId;
    private int servicePrice;
    private ServiceType serviceType;

    public Service(int serviceId, String serviceName, int servicePrice, ServiceType serviceType) {
        this.serviceId = serviceId;
        this.servicePrice = servicePrice;
        this.serviceType = serviceType;
    }

    public Service() {
    }

    @Override
    public String toString() {
        return "Service{" +
                "serviceId=" + serviceId +
                ", servicePrice=" + servicePrice +
                ", serviceType=" + serviceType +
                '}';
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public int getServicePrice() {
        return servicePrice;
    }

    public void setServicePrice(int servicePrice) {
        this.servicePrice = servicePrice;
    }

    public ServiceType getServiceType() {
        return serviceType;
    }

    public void setServiceType(ServiceType serviceType) {
        this.serviceType = serviceType;
    }
}

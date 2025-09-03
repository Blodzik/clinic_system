package clinic_system.demo.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String resourceName, int resourceId) {
        super(resourceName + " not found with id: " + resourceId);
    }
}

class Patient {
    private String patientName;
    private String nationalId;

    public Patient(String patientName, String nationalId) {
        this.patientName = patientName;
        this.nationalId = nationalId;
    }

    public String getPatientName() {
        return patientName;
    }

    public String getNationalId() {
        return nationalId;  
    }
}

class IdValidator {
    public boolean isValid(Patient patient) {
        String id = patient.getNationalId();
        return id != null && ( id.length() == 10 || id.length() == 17 );
    }
}

class SmsService {
    public void sendSmsConfirmation(Patient patient) {
        System.out.println("Sending SMS to " + patient.getPatientName() + " Registration successful.");
    }
}

public class Main3 {
    public static void main(String[] args) {
        Patient patient = new Patient("Jibran", "1234567890");
        IdValidator validator = new IdValidator();
        SmsService smsService = new SmsService();

        System.out.println("Vaccine Registration");
        if(validator.isValid(patient)) {
            smsService.sendSmsConfirmation(patient);
        }
        else {
            System.out.println("Inavlid ID for patient : " + patient.getPatientName());
        }
    }
}

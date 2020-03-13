import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Secretary_Problem {

    public static void main(String[] args) {

        List<Integer> applicantPool = getApplicants(10);
        System.out.println("Applicants: " + applicantPool);
        double eulersNumber = Math.exp(1.0);
        int applicantsAutoSkipped = (int) Math.round(applicantPool.size() / eulersNumber);
        int bestApplicant = Collections.max(applicantPool);
        int selectedApplicant = runInterviews(applicantsAutoSkipped, applicantPool);
        System.out.println("Best applicant: " + bestApplicant + "\nSelected applicant:" + selectedApplicant);
    }

    private static List<Integer> getApplicants(int numApplicants) {
        List<Integer> applicantPool = new LinkedList<>();
        while (applicantPool.size() < numApplicants) {
            int newApplicant = (int) (Math.random() * numApplicants);
            if (!applicantPool.contains(newApplicant)) {
                applicantPool.add(newApplicant);
            }
        }
        return applicantPool;
    }

    private static int runInterviews(int applicantsAutoSkipped, List<Integer> applicantPool) {
        int bestSeen = -1;

        for (int i = 0; i < applicantsAutoSkipped; i++) {
            int currentApplicant = applicantPool.get(0);
            if (currentApplicant > bestSeen) {
                bestSeen = currentApplicant;
            }
            applicantPool.remove(0);
        }
        return selectSecretary(applicantPool, bestSeen);
    }

    private static int selectSecretary(List<Integer> applicantPool, int bestSeen) {
        int finalApplicantIndex = applicantPool.size() - 1;
        for (int i = 0; i <= finalApplicantIndex; i++) {
            int currentApplicant = applicantPool.get(i);
            if (currentApplicant > bestSeen) {
                return currentApplicant;
            }
        }
        return applicantPool.get(finalApplicantIndex);
    }
}
import spock.lang.Specification

class Secretary_ProblemTest extends Specification {

    def "GetApplicants correctly returns a list of non-repeating integers"() {

        given:
        int noApplicants = 0;
        int smallApplicantNum = 25;
        int medApplicantNum = 2900
        int largeApplicantNum = 10000;
        Secretary_Problem secretaryProblem = new Secretary_Problem()

        when:
        List<Integer> emptyApplicantList = secretaryProblem.getApplicants(noApplicants)
        List<Integer> smallApplicantList = secretaryProblem.getApplicants(smallApplicantNum)
        List<Integer> midApplicantList = secretaryProblem.getApplicants(medApplicantNum)
        List<Integer> largeApplicantList = secretaryProblem.getApplicants(largeApplicantNum)

        then:
        emptyApplicantList.isEmpty()

        smallApplicantList.size() == smallApplicantNum
        smallApplicantList.unique().size() == smallApplicantNum

        midApplicantList.size() == medApplicantNum
        midApplicantList.unique().size() == medApplicantNum

        largeApplicantList.size() == largeApplicantNum
        largeApplicantList.unique().size() == largeApplicantNum
    }

    def "getAutoSkip correctly returns (applicant number * 1/e) rounded to the nearest integer"() {

        given:
        int zeroApplicants = 0;
        int tenApplicants = 10
        int twentyApplicants = 20
        int twentyOneApplicants = 21
        int oneHundredApplicants = 100
        Secretary_Problem secretaryProblem = new Secretary_Problem()

        when:
        int zeroApplicantsResult = secretaryProblem.getAutoSkip(zeroApplicants)
        int tenApplicantsResult = secretaryProblem.getAutoSkip(tenApplicants)
        int twentyApplicantsResult = secretaryProblem.getAutoSkip(twentyApplicants)
        int twentyOneApplicantsResult = secretaryProblem.getAutoSkip(twentyOneApplicants)
        int oneHundredApplicantsResult = secretaryProblem.getAutoSkip(oneHundredApplicants)

        then:
        zeroApplicantsResult == 0
        tenApplicantsResult == 4
        twentyApplicantsResult != 8
        twentyOneApplicantsResult == 8
        oneHundredApplicantsResult == 37
    }

    def "runInterviews calls selectSecretary with correct parameters"() {
        given:
        def Secretary_Problem = Spy(Secretary_Problem)
        int applicantsAutoSkipped = 4;
        List<Integer> applicantPool = [0, 1, 2, 3, 4, 5, 6, 7]
        List<Integer> expectedApplicantPool = [4, 5, 6, 7]
        int bestSeen = applicantPool[3];

        when:
        Secretary_Problem.runInterviews(applicantsAutoSkipped, applicantPool)
        Secretary_Problem.runInterviews(0, [])

        then:
        1 * Secretary_Problem.selectSecretary(expectedApplicantPool, bestSeen)
    }

    def "selectSecretary returns the algorithmically correct applicant"() {
        given:
        List<Integer> emptyApplicantPool = []
        List<Integer> orderedApplicantPool = 36..100
        List<Integer> revOrderedApplicantPool = 62..0
        List<Integer> unorderedApplicantPool = [19, 3, 33, 5, 22, 4, 11, 98, 52, 6]
        Secretary_Problem secretaryProblem = new Secretary_Problem()

        when:
        int emptyApplicantPoolSelection = secretaryProblem.selectSecretary(emptyApplicantPool, 20)
        int orderedApplicantPoolSelection = secretaryProblem.selectSecretary(orderedApplicantPool, 35)
        int revOrderedApplicantPoolSelection = secretaryProblem.selectSecretary(revOrderedApplicantPool, 63)
        int unorderedApplicantPoolSelection = secretaryProblem.selectSecretary(unorderedApplicantPool, 47)

        then:
        emptyApplicantPoolSelection == 20
        orderedApplicantPoolSelection == 36
        revOrderedApplicantPoolSelection == 0
        unorderedApplicantPoolSelection == 98
    }
}

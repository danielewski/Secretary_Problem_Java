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
}

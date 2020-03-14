import spock.lang.Specification

class Secretary_ProblemTest extends Specification {
    def "AutoSkip correctly returns (applicant number * 1/e) rounded to the nearest integer "() {

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
        int oneHunderApplicantsResult = secretaryProblem.getAutoSkip(oneHundredApplicants)

        then:
        zeroApplicantsResult == 0
        tenApplicantsResult == 4
        twentyApplicantsResult != 8
        twentyOneApplicantsResult == 8
        oneHunderApplicantsResult == 37
    }
}

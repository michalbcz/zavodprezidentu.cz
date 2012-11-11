package cz.zavodprezidentu.domain



import grails.test.mixin.*
import org.hamcrest.CoreMatchers
import org.junit.*
import static org.junit.Assert.*
import static org.hamcrest.CoreMatchers.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Account)
class AccountTests {

    @Test
    public void "account with only balance should be saved"() {
        BigDecimal balance = 1000.55

        def account = new Account(balance: balance)
        account.candidate = new Candidate(name: "Blue Kamikaze", account: account)
        account = account.save(flush: true, failOnError: true)

        def accountFromStorage = Account.findById(account.id)
        assertThat("Account has not expected balance " + balance, accountFromStorage.balance, is(1000.55))

    }
}

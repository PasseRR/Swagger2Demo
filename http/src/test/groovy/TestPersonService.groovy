import com.gome.demo.http.DemoApplication
import com.gome.demo.http.service.PersonService
import com.gome.demo.http.vo.PersonVo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.web.WebAppConfiguration
import spock.lang.Specification

@WebAppConfiguration
@ContextConfiguration(classes = DemoApplication.class)
class TestPersonService extends Specification {
    @Autowired
    PersonService personService;

    def addPerson() {
        given:
        PersonVo personVo = new PersonVo();
        personVo.setId(id);
        personVo.setIdCardNo(cardNo);
        personVo.setSex(sex);
        personVo.setName(name)

        expect:
        result == this.personService.addPerson(personVo)

        where:
        id      |       cardNo      |       name        |       sex         ||   result
        1       |       "5101"      |       "Jack"      |       "male"      ||   true
        2       |       "123456"    |       "Lucy"      |       "female"    ||   true
    }
}
import com.gome.demo.http.DemoApplication
import com.gome.demo.http.service.PersonService
import com.gome.demo.http.vo.PersonVo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.web.WebAppConfiguration
import spock.lang.Specification
import spock.lang.Unroll

@WebAppConfiguration
@ContextConfiguration(classes = DemoApplication.class)
class TestPersonService extends Specification {
    @Autowired
    PersonService personService;

    @Unroll
    // 动态spec名
    def "addPerson:(idCardNo->#idCardNo, sex->#sex, name->#name), expect:#result"() {
        // 前置条件 同setup
        given:
        def personVo = new PersonVo(
            idCardNo: idCardNo,
            sex: sex,
            name: name
        );
        // 预期
        expect:
        result == this.personService.addPerson(personVo)
        // 条件
        where:
        // 数据定义方法一
        // |用来分隔输入 ||用来分隔输出
        idCardNo | name   | sex      || result
        "5101"   | "Jack" | "male"   || true
        "123456" | "Lucy" | "female" || true
    }

    @Unroll
    def "modifyPerson:(id->#id, idCardNo->#idCardNo, sex->#sex, name->#name), expect:#result"(){
        given:
        def personVo = new PersonVo(
            id: id,
            idCardNo: idCardNo,
            sex: sex,
            name: name
        );
        expect:
        result == this.personService.modifyPerson(personVo)
        where:
        // 数据定义方法二
        id << [1, 2]
        idCardNo << ["333", "444"]
        name << ["zhangsan", "lisi"]
        sex << ["male", "male"]
        result << [true, true]
    }
}
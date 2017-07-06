import com.gome.demo.http.DemoApplication
import com.gome.demo.http.service.PersonService
import com.gome.demo.http.vo.PersonVo
import org.junit.Rule
import org.junit.rules.TestName
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
    @Rule
    TestName testName;

    // 每个spec前置
    def setupSpec(){

    }
    // 每个spec后置
    def cleanupSpec(){

    }
    // 每个方法前置
    def setup(){
        println "begin " + testName.methodName
    }
    // 每个方法后置
    def cleanup(){
        println "end " + testName.methodName
    }

    @Unroll
    // 动态spec名 {ElementType.TYPE, ElementType.METHOD}
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

    def "throw exception"(){
        given:
        Stack<?> stack = new LinkedList<>()

        when:
        stack.pop()

        then:
        thrown(EmptyStackException)
    }

    def "fetch exception"(){
        given:
        Stack<?> stack = new LinkedList<>()

        when:
        stack.pop()

        then:
        def e = thrown(EmptyStackException)
        e.cause == null
    }

    def "not thrown exception"(){
        given:
        Stack<String> stack = new LinkedList<>()
        stack.push("Hello World")

        when:
        def result = stack.pop()

        then:
        notThrown(Exception)
        result == "Hello World"
    }
}

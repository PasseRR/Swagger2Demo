package com.gome.demo.http.service;

import com.gome.demo.http.vo.OrderByVo;
import com.gome.demo.http.vo.PaginationVo;
import com.gome.demo.http.vo.PersonVo;
import com.gome.demo.http.vo.QueryParamsVo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * @author xiehai1
 * @date 2017/03/14 14:17
 * @Copyright(c) gome inc Gome Co.,LTD
 */
@Service
public class PersonService {
    private static final AtomicInteger ATOMIC_INTEGER = new AtomicInteger(1);
    private static final Map<Integer, PersonVo> PERSON_VO_MAP = new HashMap<>();

    static {
        PERSON_VO_MAP.put(ATOMIC_INTEGER.get(), new PersonVo(ATOMIC_INTEGER.getAndIncrement(), "1231", "张三", "男"));
        PERSON_VO_MAP.put(ATOMIC_INTEGER.get(), new PersonVo(ATOMIC_INTEGER.getAndIncrement(), "1232", "李四", "女"));
        PERSON_VO_MAP.put(ATOMIC_INTEGER.get(), new PersonVo(ATOMIC_INTEGER.getAndIncrement(), "1234", "王五", "女"));
        PERSON_VO_MAP.put(ATOMIC_INTEGER.get(), new PersonVo(ATOMIC_INTEGER.getAndIncrement(), "1233", "赵六", "男"));
    }

    public boolean addPerson(PersonVo personVo) {
        personVo.setId(ATOMIC_INTEGER.get());
        PERSON_VO_MAP.put(ATOMIC_INTEGER.getAndIncrement(), personVo);
        return true;
    }

    public boolean modifyPerson(PersonVo personVo) {
        PERSON_VO_MAP.put(personVo.getId(), personVo);
        return true;
    }

    public boolean deletePerson(int id) {
        PERSON_VO_MAP.remove(id);

        return true;
    }

    public PersonVo getPerson(int id) {
        return PERSON_VO_MAP.get(id);
    }

    public List<PersonVo> getPersons() {
        return PERSON_VO_MAP.values()
            .stream()
            .sorted((p1, p2) -> p1.getId() - p2.getId())
            .collect(Collectors.toList());
    }

    public List<PersonVo> queryPersons(QueryParamsVo<PersonVo> paramsVo) {
        PersonVo personVo = paramsVo.getParams();
        OrderByVo orderByVo = paramsVo.getOrderByVo();
        Comparator<PersonVo> comparator = (p1, p2) -> p1.getId() - p2.getId();
        try {
            final Field field = PersonVo.class.getDeclaredField(orderByVo.getBy());
            comparator = (p1, p2) -> {
                try {
                    int result = String.valueOf(field.get(p1)).compareTo(String.valueOf(field.get(p2)));
                    return "asc".equalsIgnoreCase(orderByVo.getOrder()) ? result : -result;
                } catch (IllegalAccessException e) {
                    return -1;
                }
            };
        } catch (Exception e) {

        }
        PaginationVo paginationVo = paramsVo.getPaginationVo();
        return PERSON_VO_MAP.values()
            .stream()
            .filter(item -> {
                // 查询过滤
                return null == personVo
                    ||
                    ((StringUtils.isEmpty(personVo.getIdCardNo()) || item.getIdCardNo().contains(personVo.getIdCardNo()))
                    && (StringUtils.isEmpty(personVo.getName()) || item.getName().contains(personVo.getName()))
                    && (StringUtils.isEmpty(personVo.getSex()) || item.getSex().equals(personVo.getSex())));
            }).sorted(comparator)
            .skip((paginationVo.getPageNumber() - 1) * paginationVo.getPageSize())
            .limit(paginationVo.getPageNumber() * paginationVo.getPageSize())
            .collect(Collectors.toList());
    }
}

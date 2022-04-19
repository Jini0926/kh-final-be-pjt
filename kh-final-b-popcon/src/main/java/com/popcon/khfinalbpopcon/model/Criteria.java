package com.popcon.khfinalbpopcon.model;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

@Data
@Component
public class Criteria {
    private int pageNum;
    private int amount;
    private String type;
    private String keyword;

    // 생성자로 무조건 1번 실행됨
    // 기본 1페이지에 12개씩 보여줌
    public Criteria() {
        this(1, 12);
    }

    // 매개변수로 들어오는 값을 이용하여 조정 가능
    public Criteria(int pageNum, int amount) {
        this.pageNum=pageNum;
        this.amount=amount;
    }

    // UricomponentsBuilder를 이용하여 링크 생성
    public String getListLink() {
        UriComponentsBuilder builder = UriComponentsBuilder.fromPath("")
                .queryParam("pageNum", pageNum)
                .queryParam("amount", amount);

        return builder.toUriString();
    }

    public String[] getTypeArr() {
        return type == null ? new String[]{} : type.split("");
    }
}

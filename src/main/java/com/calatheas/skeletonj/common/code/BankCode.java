package com.calatheas.skeletonj.common.code;

import com.calatheas.skeletonj.common.annotation.CodeClass;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@CodeClass
public enum BankCode implements CommonCode {
    B001("001", "한국은행"),
    B002("002", "산업은행"),
    B003("003", "기업은행"),
    B004("004", "국민은행"),
    B007("007", "수협은행"),
    B008("008", "수출입은행"),
    B011("011", "NH농협은행"),
    B012("012", "지역농축협"),
    B020("020", "우리은행"),
    B023("023", "SC제일은행"),
    B027("027", "한국씨티은행"),
    B031("031", "대구은행"),
    B032("032", "부산은행"),
    B034("034", "광주은행"),
    B035("035", "제주은행"),
    B037("037", "전북은행"),
    B039("039", "경남은행"),
    B041("041", "우리카드"),
    B045("045", "새마을금고"),
    B048("048", "신협"),
    B050("050", "저축은행"),
    B052("052", "모건스탠리은행"),
    B054("054", "HSBC은행"),
    B055("055", "도이치은행"),
    B057("057", "제이피모간체이스은행"),
    B058("058", "미즈호은행"),
    B059("059", "엠유에프지은행"),
    B060("060", "BOA은행"),
    B061("061", "비엔피파리바은행"),
    B062("062", "중국공상은행"),
    B063("063", "중국은행"),
    B064("064", "산림조합중앙회"),
    B065("065", "대화은행"),
    B066("066", "교통은행"),
    B067("067", "중국건설은행"),
    B071("071", "우체국"),
    B076("076", "신용보증기금"),
    B077("077", "기술보증기금"),
    B081("081", "하나은행"),
    B088("088", "신한은행"),
    B089("089", "케이뱅크"),
    B090("090", "카카오뱅크"),
    B092("092", "토스뱅크"),

    YUANTA_SECS("209", "유안타증권"),
    KB_SECS("218", "KB증권"),
    MIRAE_ASSET_DAEWOO("238", "미래에셋대우"),
    SAMSUNG_SECS("240", "삼성증권"),
    KOREA_INVESTMENT_SECS("243", "한국투자증권"),
    NH_INVESTMENT_SECS("247", "NH투자증권"),
    KYOBO_SECS("261", "교보증권"),
    HI_INVESTMENT_SECS("262", "하이투자증권"),
    HYUNDAI_MOTOR_SECS("263", "현대차증권"),
    KIWOOM_SECS("264", "키움증권"),
    EBEST_INVESTMENT_SECS("265", "이베스트투자증권"),
    SK_SECS("266", "SK증권"),
    DAISHIN_SECS("267", "대신증권"),
    HANWHA_SECS("269", "한화투자증권"),
    HANA_SECS("270", "하나금융증권"),
    SHINHAN_INVESTMENT("278", "신한금융투자"),
    DB_INVESTMENT("279", "DB금융투자"),
    EUGENE_INVESTMENT_SECS("280", "유진투자증권"),
    MERITZ_SECS("287", "메리츠증권"),
    KAKAO_PAY_SECS("288", "카카오페이증권"),
    BOOKOOK_SECS("290", "부국증권"),
    SHINYOUNG_SECS("291", "신영증권"),
    CAPE_INVESTMENT_SECS("292", "케이프투자증권"),
    KOREA_SECS("293", "한국증권금융"),
    KOREA_POS_SECS("294", "한국포스증권");

    private String code;
    private String desc;
}

package com.kr.matitting.repository;

import com.kr.matitting.constant.PartyStatus;
import com.kr.matitting.dto.PartySearchCondDto;
import com.kr.matitting.entity.Party;
import com.querydsl.core.types.NullExpression;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.kr.matitting.entity.QParty.party;


@Repository
@RequiredArgsConstructor
public class PartyRepositoryCustomImpl implements PartyRepositoryCustom{
    private final JPAQueryFactory queryFactory;

    @Override
    public Slice<Party> searchPage(PartySearchCondDto partySearchCondDto, Pageable pageable, Long lastPartyId) {
        Slice<Party> partySlice = getPartyList(partySearchCondDto, pageable, lastPartyId);
        return partySlice;
    }

    private Slice<Party> getPartyList(PartySearchCondDto partySearchCondDto, Pageable pageable, Long lastPartyId) {
        List<Party> partyList = queryFactory
                .select(party)
                .from(party)
                .where(
                        titleLike(partySearchCondDto.title()),
                        menuLike(partySearchCondDto.menu()),
                        stateEq(partySearchCondDto.status()),
                        ltPartyId(lastPartyId))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize()+1)
                .orderBy(partySort(pageable))
                .fetch();

        return checkLastPage(partyList, pageable);
    }

    private BooleanExpression titleLike(String title) {
        return StringUtils.hasText(title) ? party.partyTitle.contains(title) : null;
    }

    private BooleanExpression menuLike(String menu) {
        return StringUtils.hasText(menu) ? party.menu.contains(menu) : null;
    }

    private BooleanExpression stateEq(PartyStatus partyStatus) {
        if (ObjectUtils.isEmpty(partyStatus)) {
            return null;
        }
        return party.status.eq(partyStatus);
    }
    private BooleanExpression ltPartyId(Long lastPartyId) {
        return lastPartyId == null ? null : party.id.lt(lastPartyId);
    }

    private OrderSpecifier<?> partySort(Pageable pageable) {
        if (!pageable.getSort().isEmpty()) {
            for (Sort.Order order : pageable.getSort()) {
                Order direction = order.getDirection().isAscending() ? Order.ASC : Order.DESC;

                switch (order.getProperty()) {
                    case "HIT": //조회순
                        return new OrderSpecifier(direction, party.hit);
                    case "LATEST": //최신순(생성순)
                        return new OrderSpecifier(direction, party.createDate);
                    case "DEADLINE": //마감순
                        return new OrderSpecifier(direction, party.deadline);
                }
            }
        }
        return new OrderSpecifier(Order.ASC, NullExpression.DEFAULT, OrderSpecifier.NullHandling.Default);
    }

    private Slice<Party> checkLastPage(List<Party> partyList, Pageable pageable) {
        boolean hasNext = false;
        if (partyList.size() > pageable.getPageSize()) {
            hasNext = true;
            partyList.remove(pageable.getPageSize());
        }

        return new SliceImpl<>(partyList, pageable, hasNext);
    }
}

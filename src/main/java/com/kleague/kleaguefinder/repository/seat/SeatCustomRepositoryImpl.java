package com.kleague.kleaguefinder.repository.seat;

import com.kleague.kleaguefinder.domain.Category;
import com.kleague.kleaguefinder.domain.GameInfo;
import com.kleague.kleaguefinder.domain.Seat;
import com.kleague.kleaguefinder.request.seat.SeatCreateRequest;
import com.kleague.kleaguefinder.request.seat.SeatSearchRequest;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;


import static com.kleague.kleaguefinder.domain.QSeat.*;

@RequiredArgsConstructor
public class SeatCustomRepositoryImpl implements SeatCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Seat> findBySearchRequest(SeatSearchRequest request) {
        return jpaQueryFactory.selectFrom(seat)
                .where(seat.seatNumber.contains(request.getSeatNumber())
                        .and(seat.category.eq(request.getCategory())))
                .fetch();
    }

    @Override
    public List<Seat> findByNumberAndCategory(String seatNumber, Category category) {
         return jpaQueryFactory.selectFrom(seat)
                .where(seat.seatNumber.eq(seatNumber)
                        .and(seat.category.eq(category)))
                .fetch();
    }

}

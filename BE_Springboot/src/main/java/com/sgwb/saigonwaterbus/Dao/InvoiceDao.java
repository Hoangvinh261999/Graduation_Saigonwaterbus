package com.sgwb.saigonwaterbus.Dao;

import com.sgwb.saigonwaterbus.Model.Invoice;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface InvoiceDao extends JpaRepository<Invoice, Long> {
    Page<Invoice> findAllByCreateAt(LocalDate createAt, Pageable pageable);
    @Query("select inv from Invoice inv where inv.createAt=?1")
    Invoice findByCreateDate(LocalDateTime createDate);
    @Query("select inv from Invoice inv where inv.createAt=?1")
    Page<Invoice>  findByCreateDate1(LocalDate createAt, Pageable pageable);
    @Query(value = "WITH DistinctSeats AS ( " +
            "    SELECT DISTINCT t.invoiceid, s.seat_name " +
            "    FROM ticket t " +
            "    INNER JOIN seat s ON t.seatid = s.id " +
            "), AggregatedSeats AS ( " +
            "    SELECT invoiceid, STRING_AGG(seat_name, ',') AS seats_name " +
            "    FROM DistinctSeats " +
            "    GROUP BY invoiceid " +
            ") " +
            "SELECT " +
            "    i.id AS id, " +
            "    MIN(rou.name_route) AS name_route, " +
            "    a.seats_name AS seats_name, " +
            "    COUNT(DISTINCT t.seatid) AS seat_count, " +
            "    FORMAT(i.create_at, 'yyyy-MM-dd') AS created_at, " +
            "    CAST(i.total_amount AS FLOAT) AS total_amount, " +
            "    i.pay_method AS pay_method " +
            "FROM invoice i " +
            "LEFT JOIN ticket t ON t.invoiceid = i.id " +
            "LEFT JOIN AggregatedSeats a ON i.id = a.invoiceid " +
            "LEFT JOIN seat s ON t.seatid = s.id " +
            "LEFT JOIN ship sh ON sh.id = s.ship_id " +
            "LEFT JOIN trip tr ON tr.ship_id = sh.id AND tr.route_id = ( " +
            "    SELECT MIN(tr2.route_id) " +
            "    FROM trip tr2 " +
            "    WHERE tr2.ship_id = sh.id " +
            ") " +
            "LEFT JOIN route rou ON rou.id = tr.route_id " +
            "WHERE i.account_id = :accountId " +
            "GROUP BY i.id, i.create_at, i.total_amount, i.pay_method, a.seats_name",
            nativeQuery = true)
    List<Object[]> findInvoiceSummariesByAccountId(@Param("accountId") Long accountId);

    @Query(value = "SELECT \n" +
            "    pay_method,\n" +
            "    (COUNT(*) * 100.0 / (SELECT COUNT(*) FROM invoice WHERE delete_at IS NULL)) AS percentage\n" +
            "FROM \n" +
            "    invoice\n" +
            "WHERE \n" +
            "    delete_at IS NULL\n" +
            "GROUP BY \n" +
            "    pay_method;", nativeQuery = true)
    List<Object[]> getPercentagePaymentMethod();

    @Query(value = "WITH DateRanges AS (\n" +
            "    SELECT \n" +
            "        YEAR(GETDATE()) AS [year],\n" +
            "        1 AS [month]\n" +
            "    UNION ALL\n" +
            "    SELECT \n" +
            "        [year],\n" +
            "        [month] + 1\n" +
            "    FROM DateRanges\n" +
            "    WHERE [month] < 12\n" +
            ")\n" +
            "SELECT \n" +
            "    d.[year],\n" +
            "    d.[month],\n" +
            "    COALESCE(SUM(i.total_amount), 0) AS total_sales\n" +
            "FROM \n" +
            "    DateRanges d\n" +
            "LEFT JOIN \n" +
            "    ticket t ON YEAR(t.create_at) = d.[year] AND MONTH(t.create_at) = d.[month]\n" +
            "LEFT JOIN \n" +
            "    invoice i ON t.invoiceid = i.id\n" +
            "WHERE \n" +
            "    t.delete_at IS NULL\n" +
            "GROUP BY \n" +
            "    d.[year], d.[month]\n" +
            "ORDER BY \n" +
            "    d.[year], d.[month]\n" +
            "OPTION (MAXRECURSION 0)", nativeQuery = true)
    List<Object[]> getDoanhThuTheoThang();

    @Query(value = "SELECT SUM(total_amount) AS total_daily_revenue\n" +
            "FROM invoice\n" +
            "WHERE delete_at IS NULL AND CAST(create_at AS DATE) = CAST(GETDATE() AS DATE);", nativeQuery = true)
    Object getDoanhThuTheoNgay();

    @Query(value = "SELECT total_amount, pay_method, create_at " +
            "FROM invoice " +
            "WHERE create_at BETWEEN :startDate AND :endDate", nativeQuery = true)
    List<Object[]> getListDoanhThu(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
    @Modifying
    @Transactional
    @Query(value = "EXEC DeleteExpiredInProgressInvoices", nativeQuery = true)
    void callDeleteExpiredInProgressInvoices();


@Procedure(name = "UpdateTicketAndInvoiceComplete")
void UpdateTicketAndInvoiceComplete(@Param("emailbooking") String emailbooking);



    @Query(value = "WITH DistinctSeats AS ( " +
            "    SELECT DISTINCT t.invoiceid, s.seat_name " +
            "    FROM ticket t " +
            "    INNER JOIN seat s ON t.seatid = s.id " +
            "), AggregatedSeats AS ( " +
            "    SELECT invoiceid, STRING_AGG(seat_name, ',') AS seats_name " +
            "    FROM DistinctSeats " +
            "    GROUP BY invoiceid " +
            ") " +
            "SELECT " +
            "    i.id AS id, " +
            "    rou.name_route AS routeName, " +
            "    a.seats_name AS seatsName, " +
            "    COUNT(DISTINCT t.seatid) AS seatCount, " +
            "    FORMAT(i.create_at, 'yyyy-MM-dd') AS createdAt, " +
            "    CAST(i.total_amount AS FLOAT) AS totalAmount, " +
            "    i.pay_method AS payMethod, " +
            "    tr.id AS tripId, " +
            "    tr.departure_time AS tripStartTime, " +
            "    tr.arrival_time AS tripEndtime, " +
            "    FORMAT(tr.departure_date, 'yyyy-MM-dd') AS departureDate, " + // Thêm trường này
            "    rou.from_terminal AS fromTerminalId, " +
            "    rou.to_terminal AS toTerminalId, " +
            "    sf.name AS fromStationName, " +
            "    st.name AS toStationName, " +
            "    i.email_booking AS emailBooking " +
            "FROM invoice i " +
            "LEFT JOIN ticket t ON t.invoiceid = i.id " +
            "LEFT JOIN AggregatedSeats a ON i.id = a.invoiceid " +
            "LEFT JOIN trip tr ON t.tripid = tr.id " +
            "LEFT JOIN route rou ON rou.id = tr.route_id " +
            "LEFT JOIN station sf ON rou.from_terminal = sf.id " +
            "LEFT JOIN station st ON rou.to_terminal = st.id " +
            "WHERE i.email_booking = :email " +
            "AND i.id = :invoiceId " +
            "GROUP BY i.id, rou.name_route, a.seats_name, i.create_at, i.total_amount, i.pay_method, tr.id, tr.departure_time, tr.arrival_time, tr.departure_date, rou.from_terminal, rou.to_terminal, sf.name, st.name, i.email_booking",
            nativeQuery = true)
    List<Object[]> findInvoiceSummariesByEmailAndInvoiceId(
            String email,
            Long invoiceId
    );


}
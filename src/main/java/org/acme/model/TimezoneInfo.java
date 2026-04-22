package org.acme.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class TimezoneInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "timezone_seq")
    @SequenceGenerator(name = "timezone_seq", sequenceName = "timezone_seq", allocationSize = 1)
    private Long id;

    private String timeZone;
    private LocalDateTime dateTime;  // Promijenjeno iz currentLocalTime u dateTime
    private String ipAddress;

    @ManyToOne
    private Student student;

    // Getteri i setteri
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTimeZone() { return timeZone; }
    public void setTimeZone(String timeZone) { this.timeZone = timeZone; }

    public LocalDateTime getDateTime() { return dateTime; }
    public void setDateTime(LocalDateTime dateTime) { this.dateTime = dateTime; }

    public String getIpAddress() { return ipAddress; }
    public void setIpAddress(String ipAddress) { this.ipAddress = ipAddress; }

    public Student getStudent() { return student; }
    public void setStudent(Student student) { this.student = student; }
}
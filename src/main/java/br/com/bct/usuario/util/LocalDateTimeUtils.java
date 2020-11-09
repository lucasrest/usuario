package br.com.bct.usuario.util;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

public class LocalDateTimeUtils {

    private static String TIMEZONE = "America/Sao_Paulo";

    private static String FORMATODATA = "yyyy-MM-dd'T'HH:mm:ss";

    private LocalDateTimeUtils() {
    }

    public static LocalDateTime converterStringParaLocalDateTime(String data) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMATODATA);
            return LocalDateTime.parse(data, formatter.withZone(getZoneId()));
        } catch (Exception e) {
            try {
                return LocalDateTime.parse(data, DateTimeFormatter.ISO_DATE_TIME);
            } catch (Exception e1) {
                // Não implementado para retornar data atual
            }
            return dataAtual();
        }
    }

    public static String converterLocalDateTimeParaString(LocalDateTime localDateTime) {
        String formattedDateTime = "";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMATODATA);
        try {
            formattedDateTime = localDateTime.format(formatter);
        } catch (Exception e) {
            formattedDateTime = LocalDateTime.now().format(formatter);
        }
        return formattedDateTime;
    }

    public static LocalDateTime dataAtual() {
        return LocalDateTime.ofInstant(Instant.now(), getZoneId());
    }

    private static ZoneId getZoneId() {
        return TimeZone.getTimeZone(TIMEZONE).toZoneId();
    }

}

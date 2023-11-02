package com.example.frontend;

import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.concurrent.TimeUnit;

public class ApiService {
    private final OkHttpClient client = new OkHttpClient().newBuilder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .build();
    public void addRecord(HashSet<String> namesOfSelectedEmps, Double priceForTypeOfWashing) {

        MediaType mediaType = MediaType.parse("application/json");

        JSONObject requestBody = new JSONObject();
        requestBody.put("price", priceForTypeOfWashing);

        JSONArray empNames = new JSONArray();
        namesOfSelectedEmps.forEach(empNames::put);
        requestBody.put("empNames", empNames);

        RequestBody body = RequestBody.create(mediaType, requestBody.toString());
        Request request = new Request.Builder()
                .url("http://localhost:8080/carwash/api/v1")
                .header("Content-Type", "application/json")
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public HashSet<String> getNamesOfAllEmps() {
        Request request = new Request.Builder()
                .url("http://localhost:8080/carwash/api/v1")
                .header("Content-Type", "application/json")
                .get()
                .build();

        final HashSet<String> namesOfAllEmps = new HashSet<>();
        try (Response response = client.newCall(request).execute()) {
            String responseBody =response.body().string();
            JSONObject jsonObject = new JSONObject(responseBody);
            JSONArray array = jsonObject.getJSONArray("namesOfAllEmps");

            array.forEach(e -> namesOfAllEmps.add(e.toString()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return namesOfAllEmps;
    }

    public double calcSalary(String nameOfEmp, LocalDate date) {
        HttpUrl url = HttpUrl.parse("http://localhost:8080/carwash/api/v1/salary").newBuilder()
                .addQueryParameter("empName",nameOfEmp)
                .addQueryParameter("date",date.toString())
                .build();

        Request request = new Request.Builder()
                .url(url)
                .header("Content-Type", "application/json")
                .get()
                .build();

        BigDecimal salary = new BigDecimal(0);
        try (Response response = client.newCall(request).execute()) {
            String responseBody =response.body().string();
            JSONObject jsonObject = new JSONObject(responseBody);
            salary = (BigDecimal) jsonObject.get("salary");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return salary.doubleValue();
    }
}

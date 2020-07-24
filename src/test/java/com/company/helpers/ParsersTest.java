package com.company.helpers;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ParsersTest {
    private String formData;
    Map<String, String> actualMap;

    @Test
    public void should_returnMapWithKeyAndValueStrings_when_providedFormDataAsString() {
        // name=zxc&surname=serverS&login=qweasd&password=pass
        formData = "name=zxc&surname=server&login=qweasd&password=pass";

        actualMap = Parsers.parseFormData(formData);
        Map<String, String> expectedMap = new HashMap<>();
        expectedMap.put("name", "zxc");
        expectedMap.put("surname", "server");
        expectedMap.put("login", "qweasd");
        expectedMap.put("password", "pass");

        assertEquals(expectedMap, actualMap);
    }

    @Test
    public void should_returnNull_when_providedEmptyFormDataAsString() {
        formData = "";
        actualMap = Parsers.parseFormData(formData);
        assertNull(actualMap);
    }

}
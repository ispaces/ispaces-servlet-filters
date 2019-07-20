Cookies and Java

Get Cookie using Java 1.8 stream API
```
	Cookie customCookie = Arrays.stream(req.getCookies()).filter(c -> c.getName().equals("MY_CUSTOM_COOKIE")).findAny().orElse(null);
```

Cookie.setMaxAge()
```
loginCookie.setMaxAge(DAYS.toSeconds(1)); // cookie expires after one day
```

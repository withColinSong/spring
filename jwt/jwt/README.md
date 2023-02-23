<!-- TOC -->
# 목차
* [spec.](#spec)
  * [jwt](#jwt)
<!-- TOC -->

## spec.
- java 17
- gradle
- spring Boot 3.0.2


## jwt

- use to `postman` or `curl`

1. Use
```text
# curl
curl -XPOST song:password@localhost:8080/token

# postman
POST song:password@localhost:8080/token
```

2. result jwt
```text
eyJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJzZWxmIiwic3ViIjoic29uZyIsImV4cCI6MTY3NzE5MjcyNSwiaWF0IjoxNjc3MTU2NzI1LCJzY29wZSI6ImFwcCJ9.hUBF3656WUpLZQ2cQGNf5zh41UsLblsPIGPAj6Gos_rp6oWJz_S8WgSH4k-cbQ0_CtmRstfxoWdrj2AzlkONcEESm0skfvlqJ1GbvKHOawF0w5p87DYJlEnl8HSVTung0vMHelGSiIYC40iQRiV51prqHFUDnk3VKhm1GvVMHHQZ2Fvo18TY3Qu1Que4mDvyc54XZ_VvQOmQPuRCIDsX_FmblazLeAKET5zeoIXcEV9GIlq9PmNzWNGnGqFjQ5AQKNMT5I8gSNUe_g5oacUwcBNWMJ7RgSbY-T1Y2XDYF1InwdjR7J3B4CiMGMCs8IInYSfUqK5PIXLMMGd8vjZuKg%
```

3. run
```text
# curl
export TOKEN=`curl -XPOST song:password@localhost:8080/token`
curl -H "Authorization: Bearer $TOKEN" localhost:8080 && echo

# postman
GET localhost:8080
```
![img.png](asset/img.png)

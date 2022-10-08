package cn.com.gatico.huaweiAp;

import com.google.gson.Gson;
import com.huawei.cloudcampus.api.ApiClient;
import com.huawei.cloudcampus.api.ApiException;
import com.huawei.cloudcampus.api.model.CommonAuthorizationOutputDto;
import com.huawei.cloudcampus.api.model.CutUserInputDto;
import com.huawei.cloudcampus.api.model.CutUserOutputDto;
import com.huawei.cloudcampus.api.model.UserAuthorizationInputDto;
import com.huawei.cloudcampus.api.service.ClientUserManagerApi;

import java.util.Arrays;

public class Test {
    //冒泡排序

    @org.junit.Test
    public void sort() {
        int[] list = new int[]{10, 2, 3, 4, 56, 7, 89,};

        for (int i = 0; i < list.length; i++) {
            for (int j = 0; j < list.length - 1 - i; j++) {
                if (list[j] > list[j + 1]) {
                    int tmp = list[j + 1];
                    list[j + 1] = list[j];
                    list[j] = tmp;
                }
            }
        }
        System.out.println(Arrays.toString(list));
    }

    // Gson
    static Gson gson = new Gson();
    static ClientUserManagerApi api = null;

    public static void main(String[] args) {
        // init Api
//        String tenantName = "watsons";
//        String tenantPwd = "W@ts0ns2021";


//        String  tenantName = "465231675@qq.com";
//        String tenantPwd = "Fxd123456.";

//        String tenantName = "Watsonsapi02";
//        String tenantPwd = "Huawei@123456.";

        String tenantName = "portalapi";
        String tenantPwd = "Huawei@123.";
        String host = "cn5.naas.huaweicloud.com";
        String port = "18002";
        ApiClient apiClient = new ApiClient();
        apiClient.setTenantName(tenantName);
        apiClient.setTenantPwd(tenantPwd);
        apiClient.setHost(host);
        apiClient.setPort(port);
        api = new ClientUserManagerApi(apiClient);
        login();
    }

        public static void login() {
            //授权用户
            try {
                // body
                String bodyJson = "" +
                        "{\n" +
                        "    \"deviceMac\": \"1C-3D-2F-39-B3-20\",\n" +
                        "    \"ssid\": \"V0FUU09OU1dJRkk=\",\n" +
                        "    \"terminalIpV4\": \"172.100.102.49\",\n" +
                        "    \"terminalMac\": \"42:d6:d6:f4:c2:d3\",\n" +
                        "    \"userName\": \"42d6d6f4c2d3\",\n" +
                        "    \"nodeIp\": \"10.21.10.51\"\n" +
                        "}";
                UserAuthorizationInputDto body = gson.fromJson(bodyJson, UserAuthorizationInputDto.class);

                // access api
                CommonAuthorizationOutputDto response = api.userAuthorization(body);
                String result = gson.toJson(response);
                System.out.println(result);
            } catch (ApiException e) {
                e.printStackTrace();
            }
        }
        public static void search () {
            //查询授权
            try {
                // queryParams and pathParams
                String psessionid = "8558c6008dc84cf94b33a3d0c26ca52a0caec4af290fdd83";
                String nodeIp = "10.21.10.51";

                // access api
                CommonAuthorizationOutputDto response = api.getAuthorizationresult(psessionid, nodeIp);
                String result = gson.toJson(response);
                System.out.println(result);
            } catch (ApiException e) {
                e.printStackTrace();
            }
        }

        public static void cut () {
            //强制下线
            try {
                // body
                String bodyJson = "{'thirdUserInfos':[{'deviceMac':'1C-3D-2F-39-B3-20','terminalIpV4':'172.100.102.53','terminalMac':'74dfbf1be60e','userName':'74dfbf1be60e','psessionid':'8558c6008dc84cf94b33a3d0c26ca52a0caec4af290fdd83','nodeIp':'10.21.10.51'}]}";
                CutUserInputDto body = gson.fromJson(bodyJson, CutUserInputDto.class);

                // access api
                CutUserOutputDto response = api.cutUser(body);
                String result = gson.toJson(response);
                System.out.println(result);
            } catch (ApiException e) {
                e.printStackTrace();
            }
        }
    }

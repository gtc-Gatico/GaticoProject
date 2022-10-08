package cn.com.gatico.enum2;

import java.math.BigInteger;

public enum TestEnums {

    GET_CONTROL_CPE_LIST("查看控制管理UCPE列表", "/user/cpes", "GET", BigInteger.ONE.shiftLeft(0)),
    GET_CONTROL_CPE_INFO("查看控制管理UCPE信息", "/user/cpes/*", "GET", BigInteger.ONE.shiftLeft(0)),
    GET_CONTROL_CPE_EXPORT_SETTING("控制管理导出配置", "/user/cpes/*/services/export/config", "GET", BigInteger.ONE.shiftLeft(0)),
    POST_CONTROL_CPE_SERVICES_REBOOT("控制管理重启设备", "/user/cpes/*/services/reboot", "POST", BigInteger.ONE.shiftLeft(1)),
    POST_CONTROL_CPE_IMPORT_SETTING("控制管理导入配置", "/user/cpes/*/services/import/config", "POST", BigInteger.ONE.shiftLeft(1)),

    GET_CONTROL_LINK_GLINK("查看控制管理互联列表", "/user/glinks", "GET", BigInteger.ONE.shiftLeft(2)),
    GET_CONTROL_LINK_GLINK_BGP_ROUTE_MAP("查看控制管理互联列表", "/user/glinks/*/bgp/route_maps", "GET", BigInteger.ONE.shiftLeft(2)),
    GET_CONTROL_LINK_GLINK_ALARM_POLICIES("查看控制管理互联列表", "/user/glinks/*/alarm/policies", "GET", BigInteger.ONE.shiftLeft(2)),
    GET_CONTROL_LINK_LINK_ALARM_POLICIES("查看控制管理互联列表", "/user/links/*/alarm/policies", "GET", BigInteger.ONE.shiftLeft(2)),
    GET_CONTROL_LINK_ILINK_ALARM_POLICIES("查看控制管理互联列表", "/user/ilinks/*/alarm/policies", "GET", BigInteger.ONE.shiftLeft(2)),
    GET_CONTROL_LINK_ILINK_ALARM_METRICS("查看控制管理互联列表", "/user/glinks/*/alarm/metrics", "GET", BigInteger.ONE.shiftLeft(2)),
    GET_CONTROL_LINK_GLINK_ILINK("查看控制管理互联列表", "/user/glinks/*/ilinks", "GET", BigInteger.ONE.shiftLeft(2)),

    POST_CONTROL_LINK_LINK("创建互联管理经典互联", "/user/links", "POST", BigInteger.ONE.shiftLeft(3)),
    POST_CONTROL_LINK_GLINK("创建互联管理7xPath互联", "/user/glinks", "POST", BigInteger.ONE.shiftLeft(3)),
    PUT_CONTROL_LINK_LINK("修改互联管理经典互联", "/user/links/*", "PUT", BigInteger.ONE.shiftLeft(3)),
    PUT_CONTROL_LINK_GLINK("修改互联管理7xPath互联", "/user/glinks/*", "PUT", BigInteger.ONE.shiftLeft(3)),
    POST_CONTROL_LINK_BGP_ROUTE_MAP("创建互联管理GLINK BGP RouteMap", "/user/glinks/*/bgp/route_maps", "POST", BigInteger.ONE.shiftLeft(3)),
    DELETE_CONTROL_LINK_LINK_ALARM_POLICIES("删除互联管理经典互联告警策略", "/user/links/*/alarm/policies/*", "DELETE", BigInteger.ONE.shiftLeft(3)),
    PUT_CONTROL_LINK_LINK_ALARM_POLICIES("修改互联管理经典互联告警策略", "/user/links/*/alarm/policies/*", "PUT", BigInteger.ONE.shiftLeft(3)),
    POST_CONTROL_LINK_LINK_ALARM_POLICIES("创建互联管理经典互联告警策略", "/user/links/*/alarm/policies", "POST", BigInteger.ONE.shiftLeft(3)),
    DELETE_CONTROL_LINK_GLINK_ALARM_POLICIES("删除互联管理7xPath互联告警策略", "/user/glinks/*/alarm/policies/*", "DELETE", BigInteger.ONE.shiftLeft(3)),
    PUT_CONTROL_LINK_GLINK_ALARM_POLICIES("修改互联管理7xPath互联告警策略", "/user/glinks/*/alarm/policies/*", "PUT", BigInteger.ONE.shiftLeft(3)),
    POST_CONTROL_LINK_GLINK_ALARM_POLICIES("创建互联管理7xPath互联告警策略", "/user/glinks/*/alarm/policies", "POST", BigInteger.ONE.shiftLeft(3)),
    PUT_CONTROL_LINK_GLINK_ILINK_TRAFFIC_POLICY("修改互联管理7xPath互联iLINK流量调度策略", "/user/glinks/*/ilinks/*/traffic_scheduling_policy", "PUT", BigInteger.ONE.shiftLeft(3)),
    PUT_CONTROL_LINK_GLINK_ILINK("修改互联管理7xPath互联iLINK", "/user/glinks/*/ilinks/*", "PUT", BigInteger.ONE.shiftLeft(3)),
    DELETE_CONTROL_LINK_GLINK_ILINK("删除互联管理7xPath互联iLINK", "/user/glinks/*/ilinks/*", "DELETE", BigInteger.ONE.shiftLeft(3)),
    POST_CONTROL_LINK_GLINK_ILINK("创建互联管理7xPath互联iLINK", "/user/glinks/*/ilinks", "POST", BigInteger.ONE.shiftLeft(3)),

    GET_CONTROL_LEVEL_LIST("查看区域管理列表", "/user/levels", "GET", BigInteger.ONE.shiftLeft(4)),

    POST_CONTROL_LEVEL_LEVEL("创建区域管理区域", "/user/levels", "POST", BigInteger.ONE.shiftLeft(5)),
    PUT_CONTROL_LEVEL_LEVEL("修改区域管理区域", "/user/levels", "PUT", BigInteger.ONE.shiftLeft(5)),
    DELETE_CONTROL_LEVEL_LEVEL("删除区域管理区域", "/user/levels", "DELETE", BigInteger.ONE.shiftLeft(5)),

    GET_GLOBAL_DOMAIN_COLLECTION("查看全局管理域名集合列表", "/user/global_configuration/nat/advanced_snat/domain_collections", "GET", BigInteger.ONE.shiftLeft(6)),
    GET_GLOBAL_DOMAIN_COLLECTION_DOMAIN("查看全局管理域名集合域名列表", "/user/global_configuration/nat/advanced_snat/domain_collections/*/domains", "GET", BigInteger.ONE.shiftLeft(6)),
    GET_GLOBAL_DOMAIN_COLLECTION_DOMAIN_EXPORT("导出全局管理域名集合域名列表", "/user/global_configuration/nat/advanced_snat/domian_collections/*/services/domains_export", "GET", BigInteger.ONE.shiftLeft(6)),

    PUT_GLOBAL_DOMAIN_COLLECTION("修改全局管理域名集合", "/user/global_configuration/nat/advanced_snat/domain_collections/*", "PUT", BigInteger.ONE.shiftLeft(7)),
    DELETE_GLOBAL_DOMAIN_COLLECTION("删除全局管理域名集合", "/user/global_configuration/nat/advanced_snat/domain_collections/*", "DELETE", BigInteger.ONE.shiftLeft(7)),
    DELETE_GLOBAL_DOMAIN_COLLECTION_DOMAIN("删除全局管理域名集合域名", "/user/global_configuration/nat/advanced_snat/domain_collections/*/domains/*", "DELETE", BigInteger.ONE.shiftLeft(7)),
    PUT_GLOBAL_DOMAIN_COLLECTION_DOMAIN("修改全局管理域名集合域名", "/user/global_configuration/nat/advanced_snat/domain_collections/*/domains", "PUT", BigInteger.ONE.shiftLeft(7)),
    POST_GLOBAL_DOMAIN_COLLECTION_DOMAIN("创建全局管理域名集合域名", "/user/global_configuration/nat/advanced_snat/domain_collections/*/domains", "POST", BigInteger.ONE.shiftLeft(7)),
    POST_GLOBAL_DOMAIN_COLLECTION_DOMAIN_IMPORT("导入全局管理域名集合域名", "/user/global_configuration/nat/advanced_snat/domian_collections/*/services/domains_import", "POST", BigInteger.ONE.shiftLeft(7)),

    GET_GLOBAL_CRDI_COLLECTION("查看全局管理CRDI集合", "/user/global_configuration/nat/advanced_snat/cidr_collections", "GET", BigInteger.ONE.shiftLeft(8)),
    GET_GLOBAL_CRDI_COLLECTION_CRDI("查看全局管理CRDI集合CIDR", "/user/global_configuration/nat/advanced_snat/cidr_collections/*/cidrs", "GET", BigInteger.ONE.shiftLeft(8)),
    GET_GLOBAL_CRDI_COLLECTION_CRDI_EXPORT("导出全局管理CRDI集合CIDR", "/user/global_configuration/nat/advanced_snat/cidr_collections/*/services/cidrs_export", "GET", BigInteger.ONE.shiftLeft(8)),

    POST_GLOBAL_CRDI_COLLECTION("创建全局管理CRDI集合", "/user/global_configuration/nat/advanced_snat/cidr_collections/*/services/cidrs_import", "POST", BigInteger.ONE.shiftLeft(9)),
    PUT_GLOBAL_CRDI_COLLECTION("修改全局管理CRDI集合", "/user/global_configuration/nat/advanced_snat/cidr_collections/*", "PUT", BigInteger.ONE.shiftLeft(9)),
    DELETE_GLOBAL_CRDI_COLLECTION("删除全局管理CRDI集合", "/user/global_configuration/nat/advanced_snat/cidr_collections/*", "DELETE", BigInteger.ONE.shiftLeft(9)),
    DELETE_GLOBAL_CRDI_COLLECTION_CRDI("删除全局管理CRDI集合CIDR", "/user/global_configuration/nat/advanced_snat/cidr_collections/*/cidrs/*", "DELETE", BigInteger.ONE.shiftLeft(9)),
    POST_GLOBAL_CRDI_COLLECTION_CRDI("创建全局管理CRDI集合CIDR", "/user/global_configuration/nat/advanced_snat/cidr_collections/*/cidrs", "POST", BigInteger.ONE.shiftLeft(9)),
    POST_GLOBAL_CRDI_COLLECTION_CRDI_IMPORT("导入全局管理CRDI集合CIDR", "/user/global_configuration/nat/advanced_snat/cidr_collections/*/services/cidrs_import", "POST", BigInteger.ONE.shiftLeft(9)),

    GET_NETWORK_WAN_LIST("查看网络管理WAN列表", "/user/cpes/*/configuration/network_interface/wans", "GET", BigInteger.ONE.shiftLeft(10)),
    GET_NETWORK_GLOBAL_DNS("查看网络管理全局DNS", "/user/cpes/*/configuration/network_interface/global_dns", "GET", BigInteger.ONE.shiftLeft(10)),

    PUT_NETWORK_WAN_SETTING("修改网络管理WAN信息", "/user/cpes/*/configuration/network_interface/wans/*", "PUT", BigInteger.ONE.shiftLeft(11)),
    POST_NETWORK_WAN_RESTART("重启网络管理WAN", "/user/cpes/*/configuration/network_interface/wans/*/service/restart", "POST", BigInteger.ONE.shiftLeft(11)),
    POST_NETWORK_WAN_BANDWIDTH_TESTING("测试网络管理WAN带宽", "/user/cpes/*/configuration/network_interface/wans/*/services/bandwidth_testing", "POST", BigInteger.ONE.shiftLeft(11)),
    PUT_NETWORK_GLOBAL_DNS("修改网络管理全局DNS", "/user/cpes/*/configuration/network_interface/global_dns", "PUT", BigInteger.ONE.shiftLeft(11)),

    GET_NETWORK_LAN_LIST("查看网络管理LAN管理LAN列表", "/user/cpes/*/configuration/network_interface/lans", "GET", BigInteger.ONE.shiftLeft(12)),
//    GET_NETWORK_LAN_VLAN_LIST("查看网络管理VLAN信息", "/user/cpes/*/configuration/network_interface/vlans", "GET", BigInteger.ONE.shiftLeft(12)),
    GET_NETWORK_LAN_LAN_IP_LIST("查看网络管理LAN管理LAN IP列表", "/user/cpes/*/configuration/network_interface/lans/*/ips", "GET", BigInteger.ONE.shiftLeft(12)),
    GET_NETWORK_LAN_LAN_IP_INFO("查看网络管理LAN管理LAN IP信息", "/user/cpes/*/configuration/network_interface/lans/*/ips/*", "GET", BigInteger.ONE.shiftLeft(12)),
    GET_NETWORK_LAN_LAN_IP_DHCP_HOST("查看网络管理LAN管理LAN IP DHCP", "/user/cpes/*/configuration/network_interface/lans/*/ips/*/dhcp/dhcp_hosts", "GET", BigInteger.ONE.shiftLeft(12)),
    GET_NETWORK_LAN_LAN_IP_DHCP_BINDING("查看网络管理LAN管理LAN IP DHCP绑定", "/user/cpes/*/configuration/network_interface/lans/*/ips/*/dhcp/dhcp_bindings", "GET", BigInteger.ONE.shiftLeft(12)),
    GET_NETWORK_LAN_SPAN_SPAN0("查看网络管理LAN管理端口镜像", "/user/cpes/*/configuration/network_interface/span/SPAN0", "GET", BigInteger.ONE.shiftLeft(12)),
    GET_NETWORK_LAN_BOND_LIST("查看网络管理LAN管理链路聚合", "/user/cpes/*/configuration/network_interface/bonds/*", "GET", BigInteger.ONE.shiftLeft(12)),
    GET_NETWORK_LAN_WLAN("查看网络管理LAN管理 WLAN", "/user/cpes/*/configuration/network_interface/wlan/instances", "GET", BigInteger.ONE.shiftLeft(12)),
    GET_NETWORK_LAN_WLAN_SETTING("查看网络管理LAN管理 WLAN配置", "/user/cpes/*/configuration/network_interface/wlan/setting", "GET", BigInteger.ONE.shiftLeft(12)),

    POST_NETWORK_LAN_LAN_RESTART("网络管理LAN管理LAN重启", "/user/cpes/*/configuration/network_interface/lans/*/service/restart", "POST", BigInteger.ONE.shiftLeft(13)),
    PUT__NETWORK_LAN_LAN("修改网络管理LAN管理LAN", "/user/cpes/*/configuration/network_interface/lans/*", "PUT", BigInteger.ONE.shiftLeft(13)),
//    POST_NETWORK_LAN_VLAN_ARP_BINDING("创建", "/user/boxes/*/base_settings/vlans/*/ips/*/arp/bindings", "POST", BigInteger.ONE.shiftLeft(13)),
    DELETE_NETWORK_LAN_LAN_IP("删除网络管理LAN管理LAN IP", "/user/cpes/*/configuration/network_interface/lans/*/ips/*", "DELETE", BigInteger.ONE.shiftLeft(13)),
    POST_NETWORK_LAN_LAN_IP("创建网络管理LAN管理LAN IP", "/user/cpes/*/configuration/network_interface/lans/*/ips", "POST", BigInteger.ONE.shiftLeft(13)),
    PUT_NETWORK_LAN_LAN_IP("修改网络管理LAN管理LAN IP", "/user/cpes/*/configuration/network_interface/lans/*/ips/*", "PUT", BigInteger.ONE.shiftLeft(13)),
    POST_NETWORK_LAN_LAN_IP_DHCP_CLEAR("清除网络管理LAN管理LAN IP DHCP", "/user/cpes/*/configuration/network_interface/lans/*/ips/*/dhcp/services/clearing", "POST", BigInteger.ONE.shiftLeft(13)),
    POST_NETWORK_LAN_LAN_IP_DHCP_RELOADING("重新加载网络管理LAN管理LAN IP DHCP", "/user/cpes/*/configuration/network_interface/lans/*/ips/*/dhcp/services/reloading", "POST", BigInteger.ONE.shiftLeft(13)),
    POST_NETWORK_LAN_LAN_IP_DHCP_BINDING("创建网络管理LAN管理LAN IP DHCP绑定", "/user/cpes/*/configuration/network_interface/lans/*/ips/*/dhcp/dhcp_bindings", "POST", BigInteger.ONE.shiftLeft(13)),
    DELETE_NETWORK_LAN_LAN_IP_DHCP_BINDING("删除网络管理LAN管理LAN IP DHCP绑定", "/user/cpes/*/configuration/network_interface/lans/*/ips/*/dhcp/dhcp_bindings/*", "DELETE", BigInteger.ONE.shiftLeft(13)),
    DELETE_NETWORK_LAN_SPAN("删除网络管理LAN管理SPAN", "/user/cpes/*/configuration/network_interface/span/*", "DELETE", BigInteger.ONE.shiftLeft(13)),
    PUT_NETWORK_LAN_SPAN_SPAN0("修改网络管理LAN管理SPAN", "/user/cpes/*/configuration/network_interface/span/SPAN0", "PUT", BigInteger.ONE.shiftLeft(13)),
    DELETE_NETWORK_LAN_BOND("删除网络管理LAN管理BOND", "/user/cpes/*/configuration/network_interface/bonds/*", "DELETE", BigInteger.ONE.shiftLeft(13)),
    PUT_NETWORK_LAN_BOND("修改网络管理LAN管理BOND", "/user/cpes/*/configuration/network_interface/bonds/*", "PUT", BigInteger.ONE.shiftLeft(13)),
    POST_NETWORK_LAN_WLAN("创建网络管理LAN管理WLAN", "/user/cpes/*/configuration/network_interface/wlan/instances", "POST", BigInteger.ONE.shiftLeft(13)),
    DELETE_NETWORK_LAN_WLAN("删除网络管理LAN管理WLAN", "/user/cpes/*/configuration/network_interface/wlan/instances/*", "DELETE", BigInteger.ONE.shiftLeft(13)),
    PUT_NETWORK_LAN_WLAN_SETTING("修改网络管理LAN管理WLAN配置", "/user/cpes/*/configuration/network_interface/wlan/setting", "PUT", BigInteger.ONE.shiftLeft(13)),
    PUT_NETWORK_LAN_WLAN("修改网络管理LAN管理WLAN", "/user/cpes/*/configuration/network_interface/wlan/instances/*", "PUT", BigInteger.ONE.shiftLeft(13)),

    GET_NETWORK_VLAN_VLAN_LIST("查看网络管理VLAN管理VLAN列表", "/user/cpes/*/configuration/network_interface/vlans", "GET", BigInteger.ONE.shiftLeft(14)),
    GET_NETWORK_VLAN_VLAN_IP_LIST("查看网络管理VLAN管理VLAN IP列表", "/user/cpes/*/configuration/network_interface/vlans/*/ips", "GET", BigInteger.ONE.shiftLeft(14)),
    GET_NETWORK_VLAN_VLAN_IP_INFO("查看网络管理VLAN管理VLAN IP信息", "/user/cpes/*/configuration/network_interface/vlans/*/ips/*", "GET", BigInteger.ONE.shiftLeft(14)),
    GET_NETWORK_VLAN_VLAN_IP_DHCP_HOST("查看网络管理VLAN管理VLAN DHCP HOST列表", "/user/cpes/*/configuration/network_interface/vlans/*/ips/*/dhcp/dhcp_hosts", "GET", BigInteger.ONE.shiftLeft(14)),
    GET_NETWORK_VLAN_VLAN_IP_DHCP_BIND("查看网络管理VLAN管理VLAN DHCP BIND列表", "/user/cpes/*/configuration/network_interface/vlans/*/ips/*/dhcp/dhcp_binding", "GET", BigInteger.ONE.shiftLeft(14)),

    POST_ENUM0("创建", "/user/cpes/*/configuration/network_interface/vlans", "POST", BigInteger.ONE.shiftLeft(15)),
    DELETE_ENUM1("删除", "/user/cpes/*/configuration/network_interface/vlans/*", "DELETE", BigInteger.ONE.shiftLeft(15)),
    PUT_ENUM2("修改", "/user/cpes/*/configuration/network_interface/vlans/*", "PUT", BigInteger.ONE.shiftLeft(15)),
    DELETE_ENUM5("删除", "/user/cpes/*/configuration/network_interface/vlans/*/ips/*", "DELETE", BigInteger.ONE.shiftLeft(15)),
    POST_ENUM6("创建", "/user/cpes/*/configuration/network_interface/vlans/*/ips", "POST", BigInteger.ONE.shiftLeft(15)),
    PUT_ENUM7("修改", "/user/cpes/*/configuration/network_interface/vlans/*/ips/*", "PUT", BigInteger.ONE.shiftLeft(15)),
    POST_ENUM8("创建", "/user/cpes/*/configuration/network_interface/vlans/*/ips/*/dhcp/services/clearing", "POST", BigInteger.ONE.shiftLeft(15)),
    POST_ENUM9("创建", "/user/cpes/*/configuration/network_interface/vlans/*/ips/*/dhcp/services/reloading", "POST", BigInteger.ONE.shiftLeft(15)),
    POST_ENUM10("创建", "/user/cpes/*/configuration/network_interface/vlans/*/ips/*/dhcp/dhcp_bindings", "POST", BigInteger.ONE.shiftLeft(15)),
    DELETE_ENUM11("删除", "/user/cpes/*/configuration/network_interface/vlans/*/ips/*/dhcp/dhcp_bindings/*", "DELETE", BigInteger.ONE.shiftLeft(15)),
    POST_ENUM12("创建", "/user/cpes/*/configuration/network_interface/vlans/*/ips/*/dhcp/dhcp_bindings", "POST", BigInteger.ONE.shiftLeft(15)),

    GET_ENUM13("查看", "/user/cpes/*/configuration/network_interface/mobile/4g", "GET", BigInteger.ONE.shiftLeft(16)),
    GET_ENUM14("查看", "/user/cpes/*/operation_information/network_interface/mobile/4g/traffic/items", "GET", BigInteger.ONE.shiftLeft(16)),

    PUT_ENUM17("修改", "/user/cpes/*/configuration/network_interface/mobile/4g", "PUT", BigInteger.ONE.shiftLeft(17)),

    GET_ENUM18("查看", "/user/cpes/*/configuration/network_interface/mobile/5g", "GET", BigInteger.ONE.shiftLeft(18)),
    GET_ENUM19("查看", "/user/cpes/*/operation_information/network_interface/mobile/5g/traffic/items", "GET", BigInteger.ONE.shiftLeft(18)),

    PUT_ENUM20("修改", "/user/cpes/*/configuration/network_interface/mobile/5g", "PUT", BigInteger.ONE.shiftLeft(19)),

    GET_ENUM21("查看", "/user/cpes/*/configuration/network_interface/arp/bindings", "GET", BigInteger.ONE.shiftLeft(20)),

    POST_ENUM22("创建", "/user/cpes/*/configuration/network_interface/arp/bindings", "POST", BigInteger.ONE.shiftLeft(21)),
    DELETE_ENUM23("删除", "/user/cpes/*/configuration/network_interface/arp/bindings/*", "DELETE", BigInteger.ONE.shiftLeft(21)),

    GET_ENUM24("查看", "/user/cpes/*/configuration/network_interface/gre_tunnels", "GET", BigInteger.ONE.shiftLeft(22)),
    GET_ENUM25("查看", "/user/cpes/*/operation_information/network_interface/gre_interfaces/*/performance/traffic/samples", "GET", BigInteger.ONE.shiftLeft(22)),
    GET_ENUM26("查看", "/user/cpes/*/operation_information/network_interface/gre_interfaces/*/performance/traffic/samples", "GET", BigInteger.ONE.shiftLeft(22)),

    POST_ENUM27("创建", "/user/cpes/*/configuration/network_interface/gre_tunnels", "POST", BigInteger.ONE.shiftLeft(23)),
    DELETE_ENUM28("删除", "/user/cpes/*/configuration/network_interface/gre_tunnels/*", "DELETE", BigInteger.ONE.shiftLeft(23)),

    PUT_ENUM29("修改", "/user/cpes/*/configuration/network_interface/gre_tunnels/*", "PUT", BigInteger.ONE.shiftLeft(23)),
    GET_ENUM30("查看", "/user/cpes/*/configuration/network_interface/ipsec_tunnels", "GET", BigInteger.ONE.shiftLeft(24)),
    GET_ENUM31("查看", "/user/cpes/*/configuration/network_interface/ipsec_tunnels/*", "GET", BigInteger.ONE.shiftLeft(24)),
    GET_ENUM32("查看", "/user/cpes/*/operation_information/network_interface/ipsec/*/performance/traffic/samples", "GET", BigInteger.ONE.shiftLeft(24)),
    GET_ENUM33("查看", "/user/cpes/*/operation_information/network_interface/ipsec/*/performance/traffic/samples", "GET", BigInteger.ONE.shiftLeft(24)),

    PUT_ENUM34("修改", "/user/cpes/*/configuration/network_interface/ipsec_tunnels/*", "PUT", BigInteger.ONE.shiftLeft(25)),
    POST_ENUM35("创建", "/user/cpes/*/configuration/network_interface/ipsec_tunnels", "POST", BigInteger.ONE.shiftLeft(25)),
    DELETE_ENUM36("删除", "/user/cpes/*/configuration/network_interface/ipsec_tunnels/*", "DELETE", BigInteger.ONE.shiftLeft(25)),

    GET_ENUM37("查看", "/user/cpes/*/configuration/acceleration/collections", "GET", BigInteger.ONE.shiftLeft(26)),

    PUT_ENUM38("修改", "/user/cpes/*/configuration/acceleration/collections/*", "PUT", BigInteger.ONE.shiftLeft(27)),

    GET_ENUM39("查看", "/user/cpes/*/configuration/acceleration/setting", "GET", BigInteger.ONE.shiftLeft(28)),

    PUT_ENUM40("修改", "/user/cpes/*/configuration/acceleration/setting", "PUT", BigInteger.ONE.shiftLeft(29)),

    GET_ENUM41("查看", "/user/cpes/*/configuration/route/static_routes", "GET", BigInteger.ONE.shiftLeft(30)),

    DELETE_ENUM42("删除", "/user/cpes/*/configuration/route/static_routes/*", "DELETE", BigInteger.ONE.shiftLeft(31)),
    POST_ENUM43("创建", "/user/cpes/*/configuration/route/static_routes", "POST", BigInteger.ONE.shiftLeft(31)),

    GET_ENUM44("查看", "/user/cpes/*/configuration/route/bgp/setting", "GET", BigInteger.ONE.shiftLeft(32)),
    GET_ENUM45("查看", "/user/cpes/*/configuration/route/bgp/prefix_lists", "GET", BigInteger.ONE.shiftLeft(32)),
    GET_ENUM46("查看", "/user/cpes/*/configuration/route/bgp/as_paths", "GET", BigInteger.ONE.shiftLeft(32)),
    GET_ENUM47("查看", "/user/cpes/*/configuration/route/bgp/cidrs", "GET", BigInteger.ONE.shiftLeft(32)),
    GET_ENUM48("查看", "/user/cpes/*/configuration/route/bgp/neighbors", "GET", BigInteger.ONE.shiftLeft(32)),
    GET_ENUM49("查看", "/user/cpes/*/configuration/route/bgp/route_maps", "GET", BigInteger.ONE.shiftLeft(32)),
    GET_ENUM50("查看", "/user/cpes/*/configuration/route/bgp/route_maps/*/sets", "GET", BigInteger.ONE.shiftLeft(32)),
    GET_ENUM51("查看", "/user/cpes/*/configuration/route/bgp/route_maps/*/matches", "GET", BigInteger.ONE.shiftLeft(32)),
    GET_ENUM52("查看", "/user/cpes/*/operation_information/route/bgp/neighbors/*/routes", "GET", BigInteger.ONE.shiftLeft(32)),

    PUT_ENUM53("修改", "/user/cpes/*/configuration/route/bgp/setting", "PUT", BigInteger.ONE.shiftLeft(33)),
    DELETE_ENUM54("删除", "/user/cpes/*/configuration/route/bgp/prefix_lists/*", "DELETE", BigInteger.ONE.shiftLeft(33)),
    POST_ENUM55("创建", "/user/cpes/*/configuration/route/bgp/prefix_lists", "POST", BigInteger.ONE.shiftLeft(33)),
    PUT_ENUM56("修改", "/user/cpes/*/configuration/route/bgp/prefix_lists/*", "PUT", BigInteger.ONE.shiftLeft(33)),
    POST_ENUM57("创建", "/user/cpes/*/configuration/route/bgp/as_paths", "POST", BigInteger.ONE.shiftLeft(33)),
    DELETE_ENUM58("删除", "/user/cpes/*/configuration/route/bgp/as_paths/*", "DELETE", BigInteger.ONE.shiftLeft(33)),
    PUT_ENUM59("修改", "/user/cpes/*/configuration/route/bgp/services/cidrs_effect", "PUT", BigInteger.ONE.shiftLeft(33)),
    POST_ENUM60("创建", "/user/cpes/*/configuration/route/bgp/cidrs", "POST", BigInteger.ONE.shiftLeft(33)),
    DELETE_ENUM61("删除", "/user/cpes/*/configuration/route/bgp/cidrs/*", "DELETE", BigInteger.ONE.shiftLeft(33)),
    POST_ENUM62("创建", "/user/cpes/*/configuration/route/bgp/neighbors", "POST", BigInteger.ONE.shiftLeft(33)),
    DELETE_ENUM63("删除", "/user/cpes/*/configuration/route/bgp/neighbors/*", "DELETE", BigInteger.ONE.shiftLeft(33)),
    PUT_ENUM64("修改", "/user/cpes/*/configuration/route/bgp/neighbors/*", "PUT", BigInteger.ONE.shiftLeft(33)),
    POST_ENUM65("创建", "/user/cpes/*/configuration/route/bgp/route_maps", "POST", BigInteger.ONE.shiftLeft(33)),
    DELETE_ENUM66("删除", "/user/cpes/*/configuration/route/bgp/route_maps/*", "DELETE", BigInteger.ONE.shiftLeft(33)),
    POST_ENUM67("创建", "/user/cpes/*/configuration/route/bgp/route_maps/*/sets", "POST", BigInteger.ONE.shiftLeft(33)),
    PUT_ENUM68("修改", "/user/cpes/*/configuration/route/bgp/route_maps/*/sets/*", "PUT", BigInteger.ONE.shiftLeft(33)),
    DELETE_ENUM69("删除", "/user/cpes/*/configuration/route/bgp/route_maps/*/sets/*", "DELETE", BigInteger.ONE.shiftLeft(33)),
    POST_ENUM70("创建", "/user/cpes/*/configuration/route/bgp/route_maps/*/matches", "POST", BigInteger.ONE.shiftLeft(33)),
    PUT_ENUM71("修改", "/user/cpes/*/configuration/route/bgp/route_maps/*/matches/*", "PUT", BigInteger.ONE.shiftLeft(33)),
    DELETE_ENUM72("删除", "/user/cpes/*/configuration/route/bgp/route_maps/*/matches/*", "DELETE", BigInteger.ONE.shiftLeft(33)),
    POST_ENUM73("创建", "/user/cpes/*/operation_information/route/bgp/services/neighbors_status_reporting", "POST", BigInteger.ONE.shiftLeft(33)),
    POST_ENUM74("创建", "/user/cpes/*/operation_information/route/bgp/neighbors/*/services/routes_reporting", "POST", BigInteger.ONE.shiftLeft(33)),
    PUT_ENUM75("修改", "/user/cpes/*/operation_information/route/bgp/neighbors/*/services/routes_cleaning", "PUT", BigInteger.ONE.shiftLeft(33)),

    GET_ENUM76("查看", "/user/cpes/*/configuration/route/ospf/setting", "GET", BigInteger.ONE.shiftLeft(34)),
    GET_ENUM77("查看", "/user/cpes/*/configuration/route/ospf/interfaces", "GET", BigInteger.ONE.shiftLeft(34)),
    GET_ENUM78("查看", "/user/cpes/*/configuration/route/ospf/cidrs", "GET", BigInteger.ONE.shiftLeft(34)),
    GET_ENUM79("查看", "/user/cpes/*/operation_information/route/ospf/neighbors", "GET", BigInteger.ONE.shiftLeft(34)),

    PUT_ENUM80("修改", "/user/cpes/*/configuration/route/ospf/setting", "PUT", BigInteger.ONE.shiftLeft(35)),
    PUT_ENUM81("修改", "/user/cpes/*/configuration/route/ospf/interfaces/*", "PUT", BigInteger.ONE.shiftLeft(35)),
    PUT_ENUM82("修改", "/user/cpes/*/configuration/route/ospf/cidrs/*", "PUT", BigInteger.ONE.shiftLeft(35)),
    DELETE_ENUM83("删除", "/user/cpes/*/configuration/route/ospf/cidrs/*", "DELETE", BigInteger.ONE.shiftLeft(35)),
    POST_ENUM84("创建", "/user/cpes/*/configuration/route/ospf/cidrs", "POST", BigInteger.ONE.shiftLeft(35)),
    POST_ENUM85("创建", "/user/cpes/*/operation_information/route/ospf/services/neighbor_reloading", "POST", BigInteger.ONE.shiftLeft(35)),

    GET_ENUM86("查看", "/user/cpes/*/configuration/nat/advanced_snat/cidr/rules", "GET", BigInteger.ONE.shiftLeft(36)),
    GET_ENUM87("查看", "/user/global_configuration/nat/advanced_snat/cidr_collections", "GET", BigInteger.ONE.shiftLeft(36)),
    GET_ENUM88("查看", "/user/cpes/*/configuration/nat/advanced_snat/domain/rules", "GET", BigInteger.ONE.shiftLeft(36)),
    GET_ENUM89("查看", "/user/global_configuration/nat/advanced_snat/domain_collections", "GET", BigInteger.ONE.shiftLeft(36)),
    GET_ENUM90("查看", "/user/cpes/*/configuration/nat/snat/rules", "GET", BigInteger.ONE.shiftLeft(36)),

    DELETE_ENUM91("删除", "/user/cpes/*/configuration/nat/advanced_snat/cidr/rules/*", "DELETE", BigInteger.ONE.shiftLeft(37)),
    POST_ENUM92("创建", "/user/cpes/*/configuration/nat/advanced_snat/cidr/services/rules_effect", "POST", BigInteger.ONE.shiftLeft(37)),
    PUT_ENUM93("修改", "/user/cpes/*/configuration/nat/advanced_snat/cidr/rules/*", "PUT", BigInteger.ONE.shiftLeft(37)),
    POST_ENUM94("创建", "/user/cpes/*/configuration/nat/advanced_snat/cidr/rules", "POST", BigInteger.ONE.shiftLeft(37)),
    DELETE_ENUM95("删除", "/user/cpes/*/configuration/nat/advanced_snat/domain/rules/*", "DELETE", BigInteger.ONE.shiftLeft(37)),
    POST_ENUM96("创建", "/user/cpes/*/configuration/nat/advanced_snat/domain/services/rules_effect", "POST", BigInteger.ONE.shiftLeft(37)),
    PUT_ENUM97("修改", "/user/cpes/*/configuration/nat/advanced_snat/domain/rules/*", "PUT", BigInteger.ONE.shiftLeft(37)),
    POST_ENUM98("创建", "/user/cpes/*/configuration/nat/advanced_snat/domain/rules", "POST", BigInteger.ONE.shiftLeft(37)),
    POST_ENUM99("创建", "/user/cpes/*/configuration/nat/snat/services/rules_effect", "POST", BigInteger.ONE.shiftLeft(37)),
    POST_ENUM100("创建", "/user/cpes/*/configuration/nat/snat/rules", "POST", BigInteger.ONE.shiftLeft(37)),
    DELETE_ENUM101("删除", "/user/cpes/*/configuration/nat/snat/rules/*", "DELETE", BigInteger.ONE.shiftLeft(37)),
    PUT_ENUM102("修改", "/user/cpes/*/configuration/nat/snat/rules/*", "PUT", BigInteger.ONE.shiftLeft(37)),
    POST_ENUM103("创建", "/user/cpes/*/advanced_settings/snats/*/services/priority_increase", "POST", BigInteger.ONE.shiftLeft(37)),
    POST_ENUM104("创建", "/user/cpes/*/advanced_settings/snats/*/services/priority_decrease", "POST", BigInteger.ONE.shiftLeft(37)),

    GET_ENUM105("查看", "/user/cpes/*/configuration/nat/port_forwarding/rules", "GET", BigInteger.ONE.shiftLeft(38)),

    DELETE_ENUM108("删除", "/user/cpes/*/configuration/nat/port_forwarding/rules*", "DELETE", BigInteger.ONE.shiftLeft(39)),
    POST_ENUM109("创建", "/user/cpes/*/configuration/nat/port_forwarding/services/rules_effect", "POST", BigInteger.ONE.shiftLeft(39)),
    POST_ENUM110("创建", "/user/cpes/*/configuration/nat/port_forwarding/rules", "POST", BigInteger.ONE.shiftLeft(39)),
    PUT_ENUM111("修改", "/user/cpes/*/configuration/nat/port_forwarding/rules/*", "PUT", BigInteger.ONE.shiftLeft(39)),

    GET_BEHAVIOR_TRANSPORT_ACL_ROLES("查看基本安全规则", "/user/cpes/*/configuration/security_rule/transport_acl/rules", "GET", BigInteger.ONE.shiftLeft(40)),
    POST_BEHAVIOR_TRANSPORT_ACL_ROLES("创建基本安全规则", "/user/cpes/*/configuration/security_rule/transport_acl/rules", "POST", BigInteger.ONE.shiftLeft(41)),
    PUT_BEHAVIOR_TRANSPORT_ACL_ROLES("修改基本安全规则", "/user/cpes/*/configuration/security_rule/transport_acl/rules/*", "PUT", BigInteger.ONE.shiftLeft(41)),
    DELETE_BEHAVIOR_TRANSPORT_ACL_ROLES("删除基本安全规则", "/user/cpes/*/configuration/security_rule/transport_acl/rules/*", "DELETE", BigInteger.ONE.shiftLeft(41)),
    POST_BEHAVIOR_TRANSPORT_ACL_ROLES_EFFECT("基本安全规则立即生效", "/user/cpes/*/configuration/security_rule/transport_acl/services/rules_effect", "POST", BigInteger.ONE.shiftLeft(41)),

    GET_BEHAVIOR_URL_INTERCEPT_ROLES("查看URL拦截规则", "/user/cpes/*/configuration/security_rule/url_intercept/rules", "GET", BigInteger.ONE.shiftLeft(42)),
    POST_BEHAVIOR_URL_INTERCEPT_ROLES("创建URL拦截规则", "/user/cpes/*/configuration/security_rule/url_intercept/rules", "POST", BigInteger.ONE.shiftLeft(43)),
    PUT_BEHAVIOR_URL_INTERCEPT_ROLES("修改URL拦截规则", "/user/cpes/*/configuration/security_rule/url_intercept/rules/*", "PUT", BigInteger.ONE.shiftLeft(43)),
    DELETE_BEHAVIOR_URL_INTERCEPT_ROLES("删除URL拦截规则", "/user/cpes/*/configuration/security_rule/url_intercept/rules/*", "DELETE", BigInteger.ONE.shiftLeft(43)),
    POST_BEHAVIOR_URL_INTERCEPT_ROLES_EFFECT("基本URL拦截立即生效", "/user/cpes/*/configuration/security_rule/url_intercept/services/url_intercept_rules_effect", "POST", BigInteger.ONE.shiftLeft(43)),

    GET_ENUM112("查看", "/user/cpes/*/configuration/security_rule/transport_acl/rules", "GET", BigInteger.ONE.shiftLeft(40)),
    POST_ENUM116("创建", "/user/cpes/*/configuration/security_rule/transport_acl/rules", "POST", BigInteger.ONE.shiftLeft(41)),
    PUT_ENUM117("修改", "/user/cpes/*/configuration/security_rule/transport_acl/rules/*", "PUT", BigInteger.ONE.shiftLeft(41)),
    DELETE_ENUM118("删除", "/user/cpes/*/configuration/security_rule/transport_acl/rules/*", "DELETE", BigInteger.ONE.shiftLeft(41)),
    POST_ENUM119("创建", "/user/cpes/*/configuration/security_rule/transport_acl/services/rules_effect", "POST", BigInteger.ONE.shiftLeft(41)),
    GET_ENUM120("查看", "/user/cpes/*/configuration/security_rule/url_intercept/rules", "GET", BigInteger.ONE.shiftLeft(42)),
    POST_ENUM121("创建", "/user/cpes/*/configuration/security_rule/url_intercept/rules", "POST", BigInteger.ONE.shiftLeft(43)),
    PUT_ENUM122("修改", "/user/cpes/*/configuration/security_rule/url_intercept/rules/*", "PUT", BigInteger.ONE.shiftLeft(43)),
    DELETE_ENUM123("删除", "/user/cpes/*/configuration/security_rule/url_intercept/rules/*", "DELETE", BigInteger.ONE.shiftLeft(43)),
    POST_ENUM124("创建", "/user/cpes/*/configuration/security_rule/url_intercept/services/url_intercept_rules_effect", "POST", BigInteger.ONE.shiftLeft(43)),
    GET_ENUM125("查看", "/user/cpes/*/configuration/traffic_control/qos/traffic_priority/application_rules", "GET", BigInteger.ONE.shiftLeft(44)),
    GET_ENUM126("查看", "api/user/cpes/*/configuration/traffic_control/qos/traffic_priority/application_rules", "GET", BigInteger.ONE.shiftLeft(45)),
    DELETE_ENUM127("删除", "/user/cpes/*/configuration/traffic_control/qos/traffic_priority/application_rules/*", "DELETE", BigInteger.ONE.shiftLeft(45)),
    PUT_ENUM128("修改", "/user/cpes/*/configuration/traffic_control/qos/traffic_priority/application_rules/*", "PUT", BigInteger.ONE.shiftLeft(45)),
    POST_ENUM129("创建", "/user/cpes/*/configuration/traffic_control/qos/traffic_priority/application_rules/*", "POST", BigInteger.ONE.shiftLeft(45)),
    GET_ENUM131("查看", "/user/cpes/*/configuration/traffic_control/qos/pipes", "GET", BigInteger.ONE.shiftLeft(46)),
    GET_ENUM133("查看", "/user/cpes/*/configuration/traffic_control/qos/transport_rules", "GET", BigInteger.ONE.shiftLeft(46)),
    GET_ENUM134("查看", "/user/cpes/*/configuration/traffic_control/qos/application_rules", "GET", BigInteger.ONE.shiftLeft(46)),
    GET_ENUM135("查看", "/user/cpes/*/configuration/traffic_control/qos/setting", "GET", BigInteger.ONE.shiftLeft(46)),
    GET_ENUM136("查看", "/user/cpes/*/configuration/traffic_control/qos/application_rules", "GET", BigInteger.ONE.shiftLeft(46)),

    GET_ENUM139("查看", "/user/cpes/*/configuration/traffic_control/qos/traffic_priority/transport_rules", "GET", BigInteger.ONE.shiftLeft(46)),
    GET_ENUM140("查看", "/user/cpes/*/configuration/traffic_control/qos/traffic_priority/application_rules", "GET", BigInteger.ONE.shiftLeft(46)),
    PUT_ENUM141("修改", "/user/cpes/*/configuration/traffic_control/qos/setting", "PUT", BigInteger.ONE.shiftLeft(47)),
    POST_ENUM142("创建", "/user/cpes/*/configuration/traffic_control/qos/pipes", "POST", BigInteger.ONE.shiftLeft(47)),
    DELETE_ENUM143("删除", "/user/cpes/*/configuration/traffic_control/qos/pipes/*", "DELETE", BigInteger.ONE.shiftLeft(47)),
    PUT_ENUM144("修改", "/user/cpes/*/configuration/traffic_control/qos/pipes/*", "PUT", BigInteger.ONE.shiftLeft(47)),
    POST_ENUM145("创建", "/user/cpes/*/configuration/traffic_control/qos/transport_rules", "POST", BigInteger.ONE.shiftLeft(47)),
    PUT_ENUM146("修改", "/user/cpes/*/configuration/traffic_control/qos/transport_rules/*", "PUT", BigInteger.ONE.shiftLeft(47)),
    DELETE_ENUM147("删除", "/user/cpes/*/configuration/traffic_control/qos/transport_rules/*", "DELETE", BigInteger.ONE.shiftLeft(47)),
    POST_ENUM148("创建", "/user/cpes/*/configuration/traffic_control/qos/application_rules", "POST", BigInteger.ONE.shiftLeft(47)),
    PUT_ENUM149("修改", "/user/cpes/*/configuration/traffic_control/qos/application_rules/*", "PUT", BigInteger.ONE.shiftLeft(47)),
    DELETE_ENUM150("删除", "/user/cpes/*/configuration/traffic_control/qos/application_rules/*", "DELETE", BigInteger.ONE.shiftLeft(47)),
    POST_ENUM151("创建", "/user/cpes/*/configuration/traffic_control/qos/application_rules", "POST", BigInteger.ONE.shiftLeft(47)),
    PUT_ENUM152("修改", "/user/cpes/*/configuration/traffic_control/qos/traffic_priority/transport_rules/*", "PUT", BigInteger.ONE.shiftLeft(47)),
    POST_ENUM153("创建", "/user/cpes/*/configuration/traffic_control/qos/traffic_priority/transport_rules", "POST", BigInteger.ONE.shiftLeft(47)),
    DELETE_ENUM154("删除", "/user/cpes/*/configuration/traffic_control/qos/traffic_priority/transport_rules/*", "DELETE", BigInteger.ONE.shiftLeft(47)),
    POST_ENUM155("创建", "/user/cpes/*/configuration/traffic_control/qos/traffic_priority/application_rules", "POST", BigInteger.ONE.shiftLeft(47)),
    DELETE_ENUM156("删除", "/user/cpes/*/configuration/traffic_control/qos/traffic_priority/application_rules/*", "DELETE", BigInteger.ONE.shiftLeft(47)),
    PUT_ENUM157("修改", "/user/cpes/*/configuration/traffic_control/qos/traffic_priority/application_rules/*", "PUT", BigInteger.ONE.shiftLeft(47)),
    GET_ENUM158("查看", "api/user/cpes/*/configuration/traffic_control/wan_policies", "GET", BigInteger.ONE.shiftLeft(48)),
    POST_ENUM159("创建", "/user/cpes/*/configuration/traffic_control/wan_policies", "POST", BigInteger.ONE.shiftLeft(49)),
    DELETE_ENUM160("删除", "/user/cpes/*/configuration/traffic_control/wan_policies/*", "DELETE", BigInteger.ONE.shiftLeft(49)),
    PUT_ENUM161("修改", "api/user/cpes/*/configuration/traffic_control/wan_policies/*", "PUT", BigInteger.ONE.shiftLeft(49)),
    PUT_ENUM162("修改", "/user/cpes/*/configuration/traffic_control/wan_policies/services/policies_effect", "PUT", BigInteger.ONE.shiftLeft(49)),
    GET_ENUM163("查看", "/user/cpes/*/configuration/traffic_control/bandwidth_limit_rules", "GET", BigInteger.ONE.shiftLeft(50)),
    POST_ENUM165("创建", "//user/cpes/*/configuration/traffic_control/bandwidth_limit_rules", "POST", BigInteger.ONE.shiftLeft(51)),
    DELETE_ENUM166("删除", "/user/cpes/*/configuration/traffic_control/bandwidth_limit_rules/*", "DELETE", BigInteger.ONE.shiftLeft(51)),
    GET_ENUM167("查看", "/user/cpes/*/configuration/advanced_settings/dns/resolve_rules", "GET", BigInteger.ONE.shiftLeft(52)),
    GET_ENUM168("查看", "/user/cpes/*/configuration/advanced_settings/dns/redirect_rules", "GET", BigInteger.ONE.shiftLeft(52)),
    POST_ENUM169("创建", "/user/cpes/*/configuration/advanced_settings/dns/resolve_rules", "POST", BigInteger.ONE.shiftLeft(53)),
    DELETE_ENUM170("删除", "/user/cpes/*/configuration/advanced_settings/dns/resolve_rules/*", "DELETE", BigInteger.ONE.shiftLeft(53)),
    POST_ENUM171("创建", "/user/cpes/*/configuration/advanced_settings/dns/redirect_rules", "POST", BigInteger.ONE.shiftLeft(53)),
    PUT_ENUM172("修改", "/user/cpes/*/configuration/advanced_settings/dns/redirect_rules/*", "PUT", BigInteger.ONE.shiftLeft(53)),
    DELETE_ENUM173("删除", "/user/cpes/*/configuration/advanced_settings/dns/redirect_rules/*", "DELETE", BigInteger.ONE.shiftLeft(53)),
    GET_ENUM174("查看", "/user/boxes/*/web_security", "GET", BigInteger.ONE.shiftLeft(54)),
    GET_ENUM175("查看", "/user/boxes/*/web_security/vlan_ips", "GET", BigInteger.ONE.shiftLeft(54)),
    PUT_ENUM176("修改", "/user/boxes/*/web_security", "PUT", BigInteger.ONE.shiftLeft(55)),
    PUT_ENUM177("修改", "/user/boxes/*/web_security/vlan_ips", "PUT", BigInteger.ONE.shiftLeft(55)),
    GET_ENUM178("查看", "/user/cpes/*/configuration/advanced_settings/sase/account", "GET", BigInteger.ONE.shiftLeft(56)),
    PUT_ENUM179("修改", "/user/cpes/*/configuration/advanced_settings/sase/account", "PUT", BigInteger.ONE.shiftLeft(57)),
    GET_ENUM180("查看", "/user/cpes/*/configuration/system_config", "GET", BigInteger.ONE.shiftLeft(58)),
    PUT_ENUM181("修改", "/user/cpes/*/configuration/system_config/*", "PUT", BigInteger.ONE.shiftLeft(59)),
    GET_ENUM182("查看", "/user/cpes/*/alarm/metrics", "GET", BigInteger.ONE.shiftLeft(60)),
    GET_ENUM183("查看", "/user/cpes/*/alarm/policies", "GET", BigInteger.ONE.shiftLeft(60)),
    GET_ENUM184("查看", "/user/alarm/groups", "GET", BigInteger.ONE.shiftLeft(60)),
    DELETE_ENUM185("删除", "/user/cpes/*/alarm/policies/*", "DELETE", BigInteger.ONE.shiftLeft(61)),
    PUT_ENUM186("修改", "/user/cpes/*/alarm/policies/*", "PUT", BigInteger.ONE.shiftLeft(61)),
    POST_ENUM187("创建", "/user/cpes/*/alarm/policies", "POST", BigInteger.ONE.shiftLeft(61)),
    PUT_ENUM190("修改", "/user/cpes/*/settings", "PUT", BigInteger.ONE.shiftLeft(63)),
    GET_ENUM191("查看", "/user/cpes/*/configuration/network_interface/vlans", "GET", BigInteger.ONE.shiftLeft(64)),
    GET_ENUM192("查看", "/user/cpes/*/configuration/network_interface/lans", "GET", BigInteger.ONE.shiftLeft(64)),
    GET_ENUM193("查看", "/user/cpes/*/configuration/netflow", "GET", BigInteger.ONE.shiftLeft(64)),
    PUT_ENUM194("修改", "/user/cpes/*/configuration/netflow", "PUT", BigInteger.ONE.shiftLeft(65)),

    GET_ENUM206("查看", "/user/configuration/high_available/ha_groups", "GET", BigInteger.ONE.shiftLeft(66)),
    GET_ENUM207("查看", "/user/configuration/high_available/ha_groups/*/cpes", "GET", BigInteger.ONE.shiftLeft(66)),
    GET_ENUM208("查看", "/user/configuration/high_available/ha_groups/*", "GET", BigInteger.ONE.shiftLeft(66)),
    GET_ENUM209("查看", "/user/configuration/high_available/ha_groups/*/ha_lans", "GET", BigInteger.ONE.shiftLeft(66)),
    GET_ENUM210("查看", "/user/configuration/high_available/ha_groups/*/ha_lans/*", "GET", BigInteger.ONE.shiftLeft(66)),
    GET_ENUM211("查看", "/user/configuration/high_available/ha_groups/*/ha_lans/*/cpes", "GET", BigInteger.ONE.shiftLeft(66)),
    GET_ENUM212("查看", "/user/configuration/high_available/ha_groups/*/ha_vlans", "GET", BigInteger.ONE.shiftLeft(66)),
    GET_ENUM213("查看", "/user/configuration/high_available/ha_groups/*/ha_vlans/*", "GET", BigInteger.ONE.shiftLeft(66)),
    GET_ENUM214("查看", "/user/configuration/high_available/ha_groups/*/ha_vlans/*/cpes", "GET", BigInteger.ONE.shiftLeft(66)),
    GET_ENUM215("查看", "/user/configuration/high_available/ha_groups/*/ha_vlans/*/cpes/*/ips", "GET", BigInteger.ONE.shiftLeft(66)),
    GET_ENUM216("查看", "/user/configuration/high_available/ha_groups/*/cpes", "GET", BigInteger.ONE.shiftLeft(66)),
    GET_ENUM217("查看", "/user/configuration/high_available/ha_groups/*/ha_wans", "GET", BigInteger.ONE.shiftLeft(66)),
    GET_ENUM218("查看", "/user/configuration/high_available/ha_groups/*/ha_wans/*", "GET", BigInteger.ONE.shiftLeft(66)),
    GET_ENUM219("查看", "/user/configuration/high_available/ha_groups/*/cpes", "GET", BigInteger.ONE.shiftLeft(66)),
    GET_ENUM220("查看", "/user/configuration/high_available/ha_groups/*/ha_wans/*/cpes", "GET", BigInteger.ONE.shiftLeft(66)),
    GET_ENUM221("查看", "/user/configuration/high_available/ha_groups/*/ha_wans/*/cpes/*/ips", "GET", BigInteger.ONE.shiftLeft(66)),
    DELETE_ENUM222("删除", "/user/configuration/high_available/ha_groups/*", "DELETE", BigInteger.ONE.shiftLeft(67)),
    POST_ENUM223("创建", "/user/configuration/high_available/ha_groups", "POST", BigInteger.ONE.shiftLeft(67)),
    PUT_ENUM224("修改", "/user/configuration/high_available/ha_groups/*", "PUT", BigInteger.ONE.shiftLeft(67)),
    POST_ENUM225("创建", "/user/configuration/high_available/ha_groups/*/cpes", "POST", BigInteger.ONE.shiftLeft(67)),
    DELETE_ENUM226("删除", "/user/configuration/high_available/ha_groups/*/cpes/*", "DELETE", BigInteger.ONE.shiftLeft(67)),
    POST_ENUM227("创建", "/user/configuration/high_available/ha_groups/*/cpes/*/services/priority_increase", "POST", BigInteger.ONE.shiftLeft(67)),
    POST_ENUM228("创建", "/user/configuration/high_available/ha_groups/*/cpes/*/services/priority_decrease", "POST", BigInteger.ONE.shiftLeft(67)),
    POST_ENUM229("创建", "/user/configuration/high_available/ha_groups/*/ha_lans", "POST", BigInteger.ONE.shiftLeft(67)),
    DELETE_ENUM230("删除", "/user/configuration/high_available/ha_groups/*/ha_lans/*", "DELETE", BigInteger.ONE.shiftLeft(67)),
    PUT_ENUM231("修改", "/user/configuration/high_available/ha_groups/*/ha_lans/*", "PUT", BigInteger.ONE.shiftLeft(67)),
    POST_ENUM232("创建", "/user/configuration/high_available/ha_groups/*/ha_lans/*/cpes", "POST", BigInteger.ONE.shiftLeft(67)),
    DELETE_ENUM233("删除", "/user/configuration/high_available/ha_groups/*/ha_lans/*/cpes/*", "DELETE", BigInteger.ONE.shiftLeft(67)),
    POST_ENUM234("创建", "/user/configuration/high_available/ha_groups/*/ha_lans/*/cpes/*/services/priority_decrease", "POST", BigInteger.ONE.shiftLeft(67)),
    POST_ENUM235("创建", "/user/configuration/high_available/ha_groups/*/ha_lans/*/cpes/*/services/priority_increase", "POST", BigInteger.ONE.shiftLeft(67)),
    POST_ENUM236("创建", "/user/configuration/high_available/ha_groups/*/ha_vlans", "POST", BigInteger.ONE.shiftLeft(67)),
    DELETE_ENUM237("删除", "/user/configuration/high_available/ha_groups/*/ha_vlans/*", "DELETE", BigInteger.ONE.shiftLeft(67)),
    PUT_ENUM238("修改", "/user/configuration/high_available/ha_groups/*/ha_vlans/*", "PUT", BigInteger.ONE.shiftLeft(67)),
    POST_ENUM239("创建", "/user/configuration/high_available/ha_groups/*/ha_vlans/*/cpes", "POST", BigInteger.ONE.shiftLeft(67)),
    DELETE_ENUM240("删除", "/user/configuration/high_available/ha_groups/*/ha_vlans/*/cpes/*", "DELETE", BigInteger.ONE.shiftLeft(67)),
    POST_ENUM241("创建", "/user/configuration/high_available/ha_groups/*/ha_vlans/*/cpes/*/services/priority_decrease", "POST", BigInteger.ONE.shiftLeft(67)),
    POST_ENUM242("创建", "/user/configuration/high_available/ha_groups/*/ha_vlans/*/cpes/*/services/priority_increase", "POST", BigInteger.ONE.shiftLeft(67)),
    PUT_ENUM243("修改", "/user/configuration/high_available/ha_groups/*/ha_wans/*", "PUT", BigInteger.ONE.shiftLeft(67)),
    POST_ENUM244("创建", "/user/configuration/high_available/ha_groups/*/ha_wans/*/cpes", "POST", BigInteger.ONE.shiftLeft(67)),
    DELETE_ENUM245("删除", "/user/configuration/high_available/ha_groups/*/ha_wans/*/cpes/*", "DELETE", BigInteger.ONE.shiftLeft(67)),
    POST_ENUM246("创建", "/user/configuration/high_available/ha_groups/*/ha_wans/*/cpes/*/services/priority_decrease", "POST", BigInteger.ONE.shiftLeft(67)),
    POST_ENUM247("创建", "/user/configuration/high_available/ha_groups/*/ha_wans/*/cpes/*/services/priority_increase", "POST", BigInteger.ONE.shiftLeft(67)),
    DELETE_ENUM248("删除", "/user/configuration/high_available/ha_groups/*/ha_wans/*", "DELETE", BigInteger.ONE.shiftLeft(67)),
    POST_ENUM249("创建", "/user/configuration/high_available/ha_groups/*/ha_wans", "POST", BigInteger.ONE.shiftLeft(67)),

    POST_ENUM201("创建", "/user/cpes/*/utility/services/ping_check", "POST", BigInteger.ONE.shiftLeft(68)),
    POST_ENUM202("创建", "/user/cpes/*/utility/services/traceroute_check", "POST", BigInteger.ONE.shiftLeft(70)),
    POST_ENUM203("创建", "/user/cpes/*/utility/services/port_check", "POST", BigInteger.ONE.shiftLeft(72)),
    POST_ENUM204("创建", "/user/cpes/*/utility/services/dig_check", "POST", BigInteger.ONE.shiftLeft(74)),
    POST_ENUM205("创建", "/user/cpes/*/utility/services/route_check", "POST", BigInteger.ONE.shiftLeft(76)),


    GET_MONITOR_LINK_LINK_TRAFFICS("查看监控管理互联状态", "/user/links/*/operation_information/performance/traffics/samples", "GET", BigInteger.ONE.shiftLeft(78)),
    GET_MONITOR_LINK_GLINK_TRAFFICS("查看监控管理GLink互联状态", "/user/glinks/*/operation_information/performance/traffics/samples", "GET", BigInteger.ONE.shiftLeft(78)),
    GET_MONITOR_LINK_ILINK_TRAFFICS("查看监控管理ILink互联状态", "/user/ilinks/*/operation_information/performance/traffics/samples", "GET", BigInteger.ONE.shiftLeft(78)),

    GET_MONITOR_TRAFFIC_ANALYSIS_IPS("查看监控管理IPS流量分析", "/user/cpes/*/operation_information/dpi/traffic/analysis/ips", "GET", BigInteger.ONE.shiftLeft(80)),
    GET_MONITOR_TRAFFIC_ANALYSIS_APPLICATIONS("查看监控管理应用流量分析", "/user/cpes/*/operation_information/dpi/traffic/analysis/applications", "GET", BigInteger.ONE.shiftLeft(80)),
    GET_MONITOR_TRAFFIC_ANALYSIS_PORT("查看监控管理端口流量分析", "/user/cpes/*/operation_information/dpi/traffic/analysis/ports", "GET", BigInteger.ONE.shiftLeft(80)),
    GET_MONITOR_TRAFFIC_HISTORY_APPLICATIONS("查看监控管理应用流量历史", "/user/cpes/*/operation_information/dpi/traffic/history/applications", "GET", BigInteger.ONE.shiftLeft(80)),
    GET_MONITOR_TRAFFIC_HISTORY_PORT("查看监控管理端口流量历史", "/user/cpes/*/operation_information/dpi/traffic/history/ports", "GET", BigInteger.ONE.shiftLeft(80)),

    GET_MONITOR_GRE_TRAFFICS("查看监控管理网络隧道流量", "/user/cpes/*/operation_information/network_interface/gre_interfaces/*/performance/traffic/samples", "GET", BigInteger.ONE.shiftLeft(84)),
    GET_MONITOR_IPSEC_TRAFFICS("查看监控管理网络隧道流量", "/user/cpes/*/operation_information/network_interface/ipsec/*/performance/traffic/samples", "GET", BigInteger.ONE.shiftLeft(84)),

    GET_MONITOR_ROUTE_STATIC_ROUTES("查看监控管理静态路由", "/user/cpes/*/configuration/route/static_routes", "GET", BigInteger.ONE.shiftLeft(86)),
    GET_MONITOR_ROUTE_OSPF_ROUTES("查看监控管理OSPF路由", "/user/cpes/*/operation_information/route/ospf/routes", "GET", BigInteger.ONE.shiftLeft(86)),
    GET_MONITOR_ROUTE_GLOBAL_ROUTES("查看监控管理全局路由", "/user/cpes/*/operation_information/route/global_routes", "GET", BigInteger.ONE.shiftLeft(86)),
    POST_MONITOR_ROUTE_OSPF_ROUTES_RELOADING("刷新监控管理OSPF路由", "/user/cpes/*/operation_information/route/ospf/services/route_reloading", "POST", BigInteger.ONE.shiftLeft(86)),
    GET_MONITOR_ROUTE_OSPF_ROUTES_SETTING("查看监控管理OSPF路由配置", "/user/cpes/*/configuration/route/ospf/setting", "GET", BigInteger.ONE.shiftLeft(86)),
    GET_MONITOR_ROUTE_BGP_ROUTES("查看监控管理BGP路由", "/user/cpes/*/operation_information/route/bgp/routes", "GET", BigInteger.ONE.shiftLeft(86)),
    POST_MONITOR_ROUTE_BGP_ROUTES_RELOADING("刷新监控管理BGP路由", "/user/cpes/*/operation_information/route/bgp/services/routes_reporting", "POST", BigInteger.ONE.shiftLeft(86)),

    GET_MONITOR_ARP_ITEM("查看监控管理ARP信息", "/user/cpes/*/operation_information/network_interface/arp/items", "GET", BigInteger.ONE.shiftLeft(88)),
    POST_MONITOR_ARP_RELOADING("刷新监控管理ARP信息", "/user/cpes/*/operation_information/network_interface/arp/services/reloading", "POST", BigInteger.ONE.shiftLeft(88)),

    GET_MONITOR_NAT_LOG("查看监控管理NAT信息", "/user/cpes/*/log_information/sys_log/nat_logs", "GET", BigInteger.ONE.shiftLeft(90)),

    GET_MONITOR_CUSTOMIZE_MONITOR("查看监控管理自定义监控", "/user/cpes/*/utility/customize_monitor/network/ping_policies", "GET", BigInteger.ONE.shiftLeft(92)),
    GET_MONITOR_CUSTOMIZE_MONITOR_QUALITY("查看监控管理自定义监控图表", "/user/cpes/*/operation_information/acceleration/performance/customize_monitor/samples/*/quality", "GET", BigInteger.ONE.shiftLeft(92)),
    POST_MONITOR_CUSTOMIZE_MONITOR_QUALITY("创建监控管理自定义监控", " /user/cpes/*/utility/customize_monitor/network/ping_policies", "POST", BigInteger.ONE.shiftLeft(93)),
    DELETE_MONITOR_CUSTOMIZE_MONITOR_QUALITY("删除监控管理自定义监控", "/user/cpes/*/utility/customize_monitor/network/ping_policies/*", "DELETE", BigInteger.ONE.shiftLeft(93)),

    GET_LOG_SYSTEM_LOG_DHCP("查看日志管理系统DHCP日志", "/user/cpes/*/log_information/sys_log/dhcp_logs", "GET", BigInteger.ONE.shiftLeft(94)),
    GET_LOG_SYSTEM_LOG_INTERFACE("查看日志管理系统接口日志", "/user/cpes/*/log_information/sys_log/interface_logs", "GET", BigInteger.ONE.shiftLeft(94)),
    GET_LOG_SYSTEM_LOG_PPPOE("查看日志管理系统PPPOE日志", "/user/cpes/*/log_information/sys_log/pppoe_logs", "GET", BigInteger.ONE.shiftLeft(94)),

    GET_LOG_SECURITY_LOG_ACL("查看日志管理ACL安全日志", "/user/cpes/*/log_information/security_log/acl/logs", "GET", BigInteger.ONE.shiftLeft(96)),
    GET_LOG_SECURITY_LOG_ACL_REPORTING("查看日志管理ACL安全日志上报", "/user/cpes/*/log_information/security_log/acl/services/log_reporting", "POST", BigInteger.ONE.shiftLeft(96)),
    GET_LOG_SECURITY_LOG_SNAT("查看日志管理SNAT安全日志", "/user/cpes/*/log_information/security_log/nat/snat/logs", "GET", BigInteger.ONE.shiftLeft(96)),
    GET_LOG_SECURITY_LOG_SNAT_REPORTING("查看日志管理SNAT安全日志上报", "/user/cpes/*/log_information/security_log/nat/snat/services/log_reporting", "POST", BigInteger.ONE.shiftLeft(96)),
    GET_LOG_SECURITY_LOG_DNAT("查看日志管理DNAT安全日志", "/user/cpes/*/log_information/security_log/nat/port_forwarding/logs", "GET", BigInteger.ONE.shiftLeft(96)),
    GET_LOG_SECURITY_LOG_DNAT_REPORTING("查看日志管理DNAT安全日志上报", "/user/cpes/*/log_information/security_log/nat/port_forwarding/services/log_reporting", "POST", BigInteger.ONE.shiftLeft(96)),

    GET_LOG_OPERATION_LOG("查看日志管理操作日志", "/user/log_information/operation_logs", "GET", BigInteger.ONE.shiftLeft(98)),
    GET_LOG_OPERATION_LOG_INFO("查看日志管理操作日志详细信息", "/user/log_information/operation_logs/*", "GET", BigInteger.ONE.shiftLeft(98)),

    GET_ALARM_ALARM_RECORDS("查看告警管理告警记录", "/user/alarm/records", "GET", BigInteger.ONE.shiftLeft(100)),
    GET_ALARM_ALARM_RECORDS_INFO("查看告警管理告警记录详细信息", "/user/alarm/records/*", "GET", BigInteger.ONE.shiftLeft(100)),
//    GET_ALARM_ALARM_RECORDS_STATISTICS("查看日志管理操作日志统计信息", "/user/workspace/statistics/alarm/overview", "GET", BigInteger.ONE.shiftLeft(92)),
    PUT_ALARM_ALARM_RECORDS_INFO("修改告警管理告警记录信息", "/user/alarm/records/*", "PUT", BigInteger.ONE.shiftLeft(101)),
    DELETE_ALARM_ALARM_RECORDS_INFO("删除告警管理告警记录详细信息", "/user/alarm/records/*", "DELETE", BigInteger.ONE.shiftLeft(101)),

    GET_ALARM_ALARM_GROUP("获取告警管理告警组信息", "/user/alarm/groups", "GET", BigInteger.ONE.shiftLeft(102)),
    POST_ALARM_ALARM_GROUP("创建告警管理告警组信息", "/user/alarm/groups", "POST", BigInteger.ONE.shiftLeft(103)),
    PUT_ALARM_ALARM_GROUP("修改告警管理告警组信息", "/user/alarm/groups/*", "PUT", BigInteger.ONE.shiftLeft(103)),
    DELETE_ALARM_ALARM_GROUP("删除告警管理告警组信息", "/user/alarm/groups/*", "DELETE", BigInteger.ONE.shiftLeft(103)),

    GET_USER_ROLE("获取账户管理角色列表", "/user/users/security/roles", "GET", BigInteger.ONE.shiftLeft(106)),
    POST_USER_ROLE("创建账户管理角色", "/user/users/security/roles", "POST", BigInteger.ONE.shiftLeft(107)),
    PUT_USER_ROLE("修改账户管理角色", "/user/users/security/roles/*", "PUT", BigInteger.ONE.shiftLeft(107)),
    DELETE_USER_ROLE("删除账户管理角色", "/user/users/security/roles/*", "DELETE", BigInteger.ONE.shiftLeft(107)),

    GET_USER_SUB_USER("获取账户管理子账户列表", "/user/users/security/accounts", "GET", BigInteger.ONE.shiftLeft(108)),
    GET_USER_SUB_USER_INFO("获取账户管理子账户信息", "/user/users/security/accounts/*", "GET", BigInteger.ONE.shiftLeft(108)),
    POST_USER_SUB_USER("创建账户管理子账户", "/user/users/security/accounts", "POST", BigInteger.ONE.shiftLeft(109)),
    PUT_USER_SUB_USER("修改账户管理子账户", "/user/users/security/accounts/*", "PUT", BigInteger.ONE.shiftLeft(109)),
    DELETE_USER_SUB_USER("删除账户管理子账户", "/user/users/security/accounts/*", "DELETE", BigInteger.ONE.shiftLeft(109)),

    GET_USER_LOGIN_LOGS("获取账户管理登录历史", "/user/log_information/login_logs", "GET", BigInteger.ONE.shiftLeft(110)),

    GET_BATCH_LAN_POLICIES("获取批量管理LAN批量配置", "/user/batch_configuration/network_interface/lan_batch_policies", "GET", BigInteger.ONE.shiftLeft(112)),
    GET_BATCH_LAN_POLICIES_TASK("获取批量管理LAN批量配置任务", "/user/batch_configuration/network_interface/lan_batch_policies/*/tasks", "GET", BigInteger.ONE.shiftLeft(112)),

    POST_BATCH_LAN_POLICIES("获取批量管理LAN批量配置", "/user/batch_configuration/network_interface/lan_batch_policies", "POST", BigInteger.ONE.shiftLeft(113)),
    DELETE_BATCH_LAN_POLICIES("删除批量管理LAN批量配置", "/user/batch_configuration/network_interface/lan_batch_policies/*", "DELETE", BigInteger.ONE.shiftLeft(113)),
    POST_BATCH_LAN_TASK_EXECUTION("批量管理LAN批量配置立即执行", "/user/batch_configuration/network_interface/lan_batch_policies/*/tasks/*/services/execution", "POST", BigInteger.ONE.shiftLeft(113)),
    DELETE_BATCH_LAN_POLICIES_TASK("删除批量管理LAN批量配置任务", "/user/batch_configuration/network_interface/vlan_batch_policies/*/tasks/*", "DELETE", BigInteger.ONE.shiftLeft(113)),

    GET_BATCH_VLAN_POLICIES("获取批量管理VLAN批量配置", "/user/batch_configuration/network_interface/vlan_batch_policies", "GET", BigInteger.ONE.shiftLeft(114)),
    GET_BATCH_VLAN_POLICIES_TASK("获取批量管理VLAN批量配置任务", "/user/batch_configuration/network_interface/vlan_batch_policies/*/tasks", "GET", BigInteger.ONE.shiftLeft(114)),

    POST_BATCH_VLAN_POLICIES("获取批量管理VLAN批量配置", "/user/batch_configuration/network_interface/vlan_batch_policies", "POST", BigInteger.ONE.shiftLeft(115)),
    DELETE_BATCH_VLAN_POLICIES("删除批量管理VLAN批量配置", "/user/batch_configuration/network_interface/vlan_batch_policies/*", "DELETE", BigInteger.ONE.shiftLeft(115)),
    POST_BATCH_VLAN_TASK_EXECUTION("批量管理VLAN批量配置立即执行", "/user/batch_configuration/network_interface/vlan_batch_policies/*/tasks/*/services/execution", "POST", BigInteger.ONE.shiftLeft(115)),
    DELETE_BATCH_VLAN_POLICIES_TASK("删除批量管理VLAN批量配置任务", "/user/batch_configuration/network_interface/vlan_batch_policies/*/tasks/*", "DELETE", BigInteger.ONE.shiftLeft(115)),

    GET_BATCH_VLAN_IP_POLICIES("获取批量管理VLANIP批量配置", "/user/batch_configuration/network_interface/vlan_ip_batch_policies", "GET", BigInteger.ONE.shiftLeft(116)),
    GET_BATCH_VLAN_IP_POLICIES_TASK("获取批量管理VLANIP批量配置任务", "/user/batch_configuration/network_interface/vlan_ip_batch_policies/*/tasks", "GET", BigInteger.ONE.shiftLeft(116)),

    POST_BATCH_VLAN_IP_POLICIES("获取批量管理VLANIP批量配置", "/user/batch_configuration/network_interface/vlan_ip_batch_policies", "POST", BigInteger.ONE.shiftLeft(117)),
    DELETE_BATCH_VLAN_IP_POLICIES("删除批量管理VLANIP批量配置", "/user/batch_configuration/network_interface/vlan_ip_batch_policies/*", "DELETE", BigInteger.ONE.shiftLeft(117)),
    POST_BATCH_VLAN_IP_TASK_EXECUTION("批量管理VLANIP批量配置立即执行", "/user/batch_configuration/network_interface/vlan_ip_batch_policies/*/tasks/*/services/execution", "POST", BigInteger.ONE.shiftLeft(117)),
    DELETE_BATCH_VLAN_IP_POLICIES_TASK("删除批量管理VLANIP批量配置任务", "/user/batch_configuration/network_interface/vlan_ip_batch_policies/*/tasks/*", "DELETE", BigInteger.ONE.shiftLeft(117)),

    GET_BATCH_TRANSPORT_ACL_POLICIES("获取批量管理基本安全规则批量配置", "/user/batch_configuration/security_rule/transport_acl_batch_policies", "GET", BigInteger.ONE.shiftLeft(118)),
    GET_BATCH_TRANSPORT_ACL_POLICIES_TASK("获取批量管理基本安全规则批量配置任务", "/user/batch_configuration/security_rule/transport_acl_batch_policies/*/tasks", "GET", BigInteger.ONE.shiftLeft(118)),

    POST_BATCH_TRANSPORT_ACL_POLICIES("获取批量管理基本安全规则批量配置", "/user/batch_configuration/security_rule/transport_acl_batch_policies", "POST", BigInteger.ONE.shiftLeft(119)),
    DELETE_BATCH_TRANSPORT_ACL_POLICIES("删除批量管理基本安全规则批量配置", "/user/batch_configuration/security_rule/transport_acl_batch_policies/*", "DELETE", BigInteger.ONE.shiftLeft(119)),
    POST_BATCH_TRANSPORT_ACL_TASK_EXECUTION("批量管理基本安全规则批量配置立即执行", "/user/batch_configuration/security_rule/transport_acl_batch_policies/*/tasks/*/services/execution", "POST", BigInteger.ONE.shiftLeft(119)),
    DELETE_BATCH_TRANSPORT_ACL_POLICIES_TASK("删除批量管理基本安全规则批量配置任务", "/user/batch_configuration/security_rule/transport_acl_batch_policies/*/tasks/*", "DELETE", BigInteger.ONE.shiftLeft(119)),

    GET_BATCH_NAT_SNAT_POLICIES("获取批量管理SNAT规则批量配置", "/user/batch_configuration/nat/snat_batch_policies", "GET", BigInteger.ONE.shiftLeft(120)),
    GET_BATCH_NAT_SNAT_POLICIES_TASK("获取批量管理SNAT规则批量配置任务", "/user/batch_configuration/nat/snat_batch_policies/*/tasks", "GET", BigInteger.ONE.shiftLeft(120)),

    POST_BATCH_NAT_SNAT_POLICIES("获取批量管理SNAT规则批量配置", "/user/batch_configuration/nat/snat_batch_policies", "POST", BigInteger.ONE.shiftLeft(121)),
    DELETE_BATCH_NAT_SNAT_POLICIES("删除批量管理SNAT规则批量配置", "/user/batch_configuration/nat/snat_batch_policies/*", "DELETE", BigInteger.ONE.shiftLeft(121)),
    POST_BATCH_NAT_SNAT_TASK_EXECUTION("批量管理SNAT规则批量配置立即执行", "/user/batch_configuration/nat/snat_batch_policies/*/tasks/*/services/execution", "POST", BigInteger.ONE.shiftLeft(121)),
    DELETE_BATCH_NAT_SNAT_POLICIES_TASK("删除批量管理SNAT安全规则批量配置任务", "/user/batch_configuration/nat/snat_batch_policies/*/tasks/*", "DELETE", BigInteger.ONE.shiftLeft(121)),

    GET_BATCH_ADVANCED_SNAT_CIDR_POLICIES("获取批量管理IP地址段批量配置", "/user/batch_configuration/nat/advanced_snat/cidr_batch_policies", "GET", BigInteger.ONE.shiftLeft(122)),
    GET_BATCH_ADVANCED_SNAT_CIDR_POLICIES_TASK("获取批量管理IP地址段批量配置任务", "/user/batch_configuration/nat/advanced_snat/cidr_batch_policies/*/tasks", "GET", BigInteger.ONE.shiftLeft(122)),

    POST_BATCH_ADVANCED_SNAT_CIDR_POLICIES("获取批量管理IP地址段批量配置", "/user/batch_configuration/nat/advanced_snat/cidr_batch_policies", "POST", BigInteger.ONE.shiftLeft(123)),
    DELETE_BATCH_ADVANCED_SNAT_CIDR_POLICIES("删除批量管理IP地址段批量配置", "/user/batch_configuration/nat/advanced_snat/cidr_batch_policies/*", "DELETE", BigInteger.ONE.shiftLeft(123)),
    POST_BATCH_ADVANCED_SNAT_CIDR_TASK_EXECUTION("批量管理IP地址段批量配置立即执行", "/user/batch_configuration/nat/advanced_snat/cidr_batch_policies/*/tasks/*/services/execution", "POST", BigInteger.ONE.shiftLeft(123)),
    DELETE_BATCH_ADVANCED_SNAT_CIDR_POLICIES_TASK("删除批量管理IP地址段批量配置任务", "/user/batch_configuration/nat/advanced_snat/cidr_batch_policies/*/tasks/*", "DELETE", BigInteger.ONE.shiftLeft(123)),

    GET_BATCH_ADVANCED_SNAT_DOMAIN_POLICIES("获取批量管理域名批量配置", "/user/batch_configuration/nat/advanced_snat/domain_batch_policies", "GET", BigInteger.ONE.shiftLeft(124)),
    GET_BATCH_ADVANCED_SNAT_DOMAIN_POLICIES_TASK("获取批量管理域名批量配置任务", "/user/batch_configuration/nat/advanced_snat/domain_batch_policies/*/tasks", "GET", BigInteger.ONE.shiftLeft(124)),

    POST_BATCH_ADVANCED_SNAT_DOMAIN_POLICIES("获取批量管理域名批量配置", "/user/batch_configuration/nat/advanced_snat/domain_batch_policies", "POST", BigInteger.ONE.shiftLeft(125)),
    DELETE_BATCH_ADVANCED_SNAT_DOMAIN_POLICIES("删除批量管理域名批量配置", "/user/batch_configuration/nat/advanced_snat/domain_batch_policies/*", "DELETE", BigInteger.ONE.shiftLeft(125)),
    POST_BATCH_ADVANCED_SNAT_DOMAIN_TASK_EXECUTION("批量管理域名批量配置立即执行", "/user/batch_configuration/nat/advanced_snat/domain_batch_policies/*/tasks/*/services/execution", "POST", BigInteger.ONE.shiftLeft(125)),
    DELETE_BATCH_ADVANCED_SNAT_DOMAIN_POLICIES_TASK("删除批量管理域名批量配置任务", "/user/batch_configuration/nat/advanced_snat/domain_batch_policies/*/tasks/*", "DELETE", BigInteger.ONE.shiftLeft(125)),

    GET_BATCH_DNS_REDIRECT_POLICIES("获取批量管理DNS重定向批量配置", "/user/batch_configuration/advanced_settings/dns_redirect_batch_policies", "GET", BigInteger.ONE.shiftLeft(126)),

    POST_BATCH_DNS_REDIRECT_POLICIES("获取批量管理DNS重定向批量配置", "/user/batch_configuration/advanced_settings/dns_redirect_batch_policies", "POST", BigInteger.ONE.shiftLeft(127)),
    DELETE_BATCH_DNS_REDIRECT_POLICIES("删除批量管理DNS重定向批量配置", "/user/batch_configuration/advanced_settings/dns_redirect_batch_policies/*", "DELETE", BigInteger.ONE.shiftLeft(127)),

    GET_BATCH_TRAFFIC_CONTROL_WAN_POLICIES("获取批量管理WAN负载批量配置", "/user/batch_configuration/traffic_control/wan_batch_policies", "GET", BigInteger.ONE.shiftLeft(128)),
    GET_BATCH_TRAFFIC_CONTROL_WAN_POLICIES_TASK("获取批量管理WAN负载批量配置任务", "/user/batch_configuration/traffic_control/wan_batch_policies/*/tasks", "GET", BigInteger.ONE.shiftLeft(128)),

    POST_BATCH_TRAFFIC_CONTROL_WAN_POLICIES("获取批量管理WAN负载批量配置", "/user/batch_configuration/traffic_control/wan_batch_policies", "POST", BigInteger.ONE.shiftLeft(129)),
    DELETE_BATCH_TRAFFIC_CONTROL_WAN_POLICIES("删除批量管理WAN负载批量配置", "/user/batch_configuration/traffic_control/wan_batch_policies/*", "DELETE", BigInteger.ONE.shiftLeft(129)),
    POST_BATCH_TRAFFIC_CONTROL_WAN_TASK_EXECUTION("批量管理WAN负载批量配置立即执行", "/user/batch_configuration/traffic_control/wan_batch_policies/*/tasks/*/services/tasks_effect", "POST", BigInteger.ONE.shiftLeft(129)),
    DELETE_BATCH_TRAFFIC_CONTROL_WAN_POLICIES_TASK("删除批量管理WAN负载批量配置任务", "/user/batch_configuration/traffic_control/wan_batch_policies/*/tasks/*", "DELETE", BigInteger.ONE.shiftLeft(129)),

    ;

    String name;
    String url;
    String method;
    BigInteger authority;

    TestEnums(String name, String url, String method, BigInteger authority) {
        this.name = name;
        this.url = url;
        this.method = method;
        this.authority = authority;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public BigInteger getAuthority() {
        return authority;
    }

    public void setAuthority(BigInteger authority) {
        this.authority = authority;
    }
}

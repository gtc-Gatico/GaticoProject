package cn.com.gatico.USB;

import javax.usb.*;
import javax.usb.event.UsbPipeDataEvent;
import javax.usb.event.UsbPipeErrorEvent;
import javax.usb.event.UsbPipeListener;
import java.util.List;

public class UsbConn {
    private static final short VENDOR_ID = 0x1ea7;
    /**
     * The product ID of the missile launcher.
     */
    private static final int PRODUCT_ID = 0x0064;
    //    private static final short VENDOR_ID = 0x10c4;
//    // /** The product ID of the missile launcher. */
//    private static final short PRODUCT_ID = -5536;
    private static UsbPipe pipe81, pipe01;

    /**
     * 依据VID和PID找到设备device
     *
     * @param hub
     * @return
     */
    @SuppressWarnings("unchecked")
    public static UsbDevice findMissileLauncher(UsbHub hub) {
        UsbDevice launcher = null;

        for (UsbDevice device : (List<UsbDevice>) hub.getAttachedUsbDevices()) {
            if (device.isUsbHub()) {
                launcher = findMissileLauncher((UsbHub) device);
                if (launcher != null)
                    return launcher;
            } else {
                UsbDeviceDescriptor desc = device.getUsbDeviceDescriptor();
                if (desc.idVendor() == VENDOR_ID
                        && desc.idProduct() == PRODUCT_ID) {
                    System.out.println("找到设备：" + device);
                    return device;
                }
            }
        }
        return null;
    }

    public static void sendMessage(UsbDevice device, byte[] message)
            throws UsbException {
        UsbControlIrp irp = device
                .createUsbControlIrp(
                        (byte) (UsbConst.REQUESTTYPE_TYPE_CLASS | UsbConst.REQUESTTYPE_RECIPIENT_INTERFACE),
                        (byte) 0x09, (short) 2, (short) 1);
        irp.setData(message);
        device.syncSubmit(irp);
    }

    /**
     * 注意权限的配置问题，在linux下可能无法打开device，解决办法参考官方的FAQ
     *
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        UsbDevice device;
        try {
            device = findMissileLauncher(UsbHostManager.getUsbServices().getRootUsbHub());
            if (device == null) {
                System.out.println("Missile launcher not found.");
                System.exit(1);
                return;
            }
            UsbConfiguration configuration = device.getActiveUsbConfiguration();//获取配置信息

            UsbInterface iface = configuration.getUsbInterface((byte) 1);//接口
            iface.claim(new UsbInterfacePolicy() {

                @Override
                public boolean forceClaim(UsbInterface arg0) {
                    // TODO Auto-generated method stub
                    return true;
                }
            });
            // for (UsbEndpoint endpoints : (List<UsbEndpoint>) iface
            // .getUsbEndpoints()) {
            // System.out.println("--->"+endpoints.getUsbEndpointDescriptor());
            // }
            UsbEndpoint endpoint81 = iface.getUsbEndpoint((byte) 0x80);//接受数据地址
//            UsbEndpoint endpoint01 = iface.getUsbEndpoint((byte) 0xa0);//发送数据地址
            pipe81 = endpoint81.getUsbPipe();

//            pipe01 = endpoint01.getUsbPipe();
            pipe81.open();
            byte[] dataSend = {(byte) 0x00};//需要发送的数据
            pipe81.asyncSubmit(dataSend);
            pipe81.addUsbPipeListener(new UsbPipeListener() {

                @Override
                public void errorEventOccurred(UsbPipeErrorEvent arg0) {
                    // TODO Auto-generated method stub
                    System.out.println(arg0);
                }

                @Override
                public void dataEventOccurred(UsbPipeDataEvent arg0) {
                    // TODO Auto-generated method stub
                    System.out.println(new String(arg0.getData()));
                }
            });
//            pipe81.open();
//             pipe81.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            //
        }
    }
}

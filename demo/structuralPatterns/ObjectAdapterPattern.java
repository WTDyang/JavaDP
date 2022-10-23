package structuralPatterns;

/**
 * 适配器模式
 *
 * @author WTDYang
 * @date 2022/10/17
 */
public class ObjectAdapterPattern {
    public static void main(String[] args) {
        USBDrive usbDrive = new USBDrive();
        TypeC usbTypeCAdapter =  new USBTypeCAdapter(usbDrive);
        usbTypeCAdapter.transferData();

    }
}
interface USB{
    public void transferData();
}
interface TypeC{
    public void transferData();
}

class USBDrive implements USB{

    @Override
    public void transferData() {
        System.out.println("U盘正在传输数据");
    }
}

class USBTypeCAdapter implements TypeC{
    private USB usb;
    public USBTypeCAdapter(USBDrive usb){
        this.usb = usb;
    }
    @Override
    public void transferData() {
        usb.transferData();
    }
}
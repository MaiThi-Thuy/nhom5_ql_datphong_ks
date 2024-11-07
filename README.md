# Quản lý khách hàng đặt phòng khách sạn

## Bộ Code Version 1
Nhóm 5:
Nguyễn Văn Hiếu:22010509
Mai Thị Thủy:23010362
1. Tài khoản login cho admin
username: admin
password: admin
2. tai khoan login cho nhan vien
username: employee
password: 0000
4. Môi trường
    1. NetBeans 23, JDK 23
    2. Dependencies: GSON, JCalendar
5. Cơ sở dữ liệu
    1. Lưu trữ JSON bằng Thư viện GSON.
    2. Gồm cơ sở dữ liệu của khách hàng và các phòng (customer.json và room.json).
6. Giao diện
    1. Sử dụng JTable cho bảng khách hàng, JCalendar cho chọn lịch checkIn/checkOut.
    Sử dụng JComboBox làm dropdown list cho chọn loại phòng khách sạn.
    2. Giá tiền định dạng 100.000.000.
7. Thực thể 
    1. Khách hàng gồm các thuộc tính: 
    int ID, 
    String Tên, 
    Int Tuổi, 
    String SĐT (10 ký tự),
    String CCCD (10 ký tự), 
    String Địa chỉ,
    Date Ngày checkIn/checkOut,
    List ID phòng đã đặt,
    double Tổng đơn giá.
    2. Phòng: 
    int ID,
    String Loại phòng,
    double Giá phòng,
    Boolean Tình trạng phòng.
8. View
    1. LoginView: Màn hình Đăng nhập.
    2. CustomerView: Màn hình quản lý chính.
    3. CustomerRoomView: Màn hình xem phòng khách đã chọn.
    4. RoomView: Màn hình quản lý phòng.
9. Chức năng
    1. Thêm/sửa/xóa khách hàng.
    2. Chọn loại phòng.
    3. Đặt phòng còn trống.
    4. Xem phòng khách đã đặt (Bấm vào cột Phòng thuê của khách tương ứng).
    5. Hủy phòng khách đã thuê.
    6. Tìm kiếm khách hàng theo từ khóa (String).
    7. Sort khách hàng theo Tên/Đơn giá.
    8. Thêm/sửa/xóa phòng.
    9. Sort phòng theo Giá phòng.
10. Xử lý ngoại lệ:
    1. Xử lý Tên trống.
    2. Xử lý tuổi trong khoảng 1-100.
    3. Xử lý Địa chỉ trống.
    4. Xử lý số điện thoại phải có 10 ký tự.
    5. Xử lý căn cước phải có 10 ký tự.
    6. Xử lý CheckIn/CheckOut trống.
11. Đã phát triển thêm:
    1. Thống kê:
    -Tổng số khách đặt phòng, bao nhiêu khách đặt phòng trong tháng, tổng đơn giá.
    -Vẽ báo cáo bằng biểu đồ cột theo từng loại phòng.
    2. Báo cáo in ra bằng PDF
    3. Bảo mật mật khẩu bằng AES.
    4. Phân quyền cho người dùng: admin hoặc employee

/**
 * 
 */
package alipay.entity;

/**
 * @author be.dev
 *
 */
public class AlipayTransfers {

	// 基本参数
	// 接口名称
	private String service;
	// 合作者身份ID(String(16))
	private String partner;
	// 参数编码字符集
	private String _input_charset;
	// 签名方式
	private String sign_type;
	// 签名
	private String sign;
	// 服务器异步通知页面路径(String(190))
	private String notify_url;
	// 页面跳转同步通知页面路径(String(200))
	private String return_url;
	// 业务参数
	// 商户网站唯一订单号(String(64))
	private String out_trade_no;
	// 商品名称(String(256))
	private String subject;
	// 支付类型(String(4))
	private String payment_type;
	// 交易金额
	private Double total_fee;
	// 卖家支付宝用户号(String(16))
	private String seller_id;
	// 卖家支付宝账号(String(100))
	private String seller_email;
	// 卖家支付宝账号别名(String(100))
	private String seller_account_name;

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getPartner() {
		return partner;
	}

	public void setPartner(String partner) {
		this.partner = partner;
	}

	public String get_input_charset() {
		return _input_charset;
	}

	public void set_input_charset(String _input_charset) {
		this._input_charset = _input_charset;
	}

	public String getSign_type() {
		return sign_type;
	}

	public void setSign_type(String sign_type) {
		this.sign_type = sign_type;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getNotify_url() {
		return notify_url;
	}

	public void setNotify_url(String notify_url) {
		this.notify_url = notify_url;
	}

	public String getReturn_url() {
		return return_url;
	}

	public void setReturn_url(String return_url) {
		this.return_url = return_url;
	}

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getPayment_type() {
		return payment_type;
	}

	public void setPayment_type(String payment_type) {
		this.payment_type = payment_type;
	}

	public Double getTotal_fee() {
		return total_fee;
	}

	public void setTotal_fee(Double total_fee) {
		this.total_fee = total_fee;
	}

	public String getSeller_id() {
		return seller_id;
	}

	public void setSeller_id(String seller_id) {
		this.seller_id = seller_id;
	}

	public String getSeller_email() {
		return seller_email;
	}

	public void setSeller_email(String seller_email) {
		this.seller_email = seller_email;
	}

	public String getSeller_account_name() {
		return seller_account_name;
	}

	public void setSeller_account_name(String seller_account_name) {
		this.seller_account_name = seller_account_name;
	}

}

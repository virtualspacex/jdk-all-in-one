package cn.com.virtualspacex.constants;

/**
 * 常量
 *
 * @author yao-chaochao
 * @date 2020/09/02
 */
public class Constant {

    /**
     * 系统版本
     */
    public static final String VERSION = "0.0.1";

    /**
     * 机能分类符
     */
    public static final String FUNCTION_SYMBOL = "VOPBATPRC";

    /**
     * 机能号
     */
    public static final String TASK_ID = "9111";

    /**
     * 机能名称
     */
    public static final String TASK_NAME = "取引通番欠番管理";

    /**
     * flagfile_dir的配置名
     */
    public static final String FLAGFILE_DIR = "flagfile_dir";


    /**
     * flagfile_name的配置名
     */
    public static final String FLAGFILE_NAME = "flagfile_name";


    /**
     * onlinesaleinfos_tablename的配置名
     */
    public static final String ONLINESALEINFOS_TABLENAME = "onlinesaleinfos_tablename";


    /**
     * sale_serial_missingnumbers_tablename的配置名
     */
    public static final String SALE_SERIAL_MISSED_TABLENAME = "sale_serial_missingnumbers_tablename";


    /**
     * sale_serial_end_tablename的配置名
     */
    public static final String SALE_SERIAL_END_TABLENAME = "sale_serial_end_tablename";


    /**
     * retry_time的配置名
     */
    public static final String RETRY_TIME = "retry_time";


    /**
     * retry_seconds的配置名
     */
    public static final String RETRY_SECONDS = "retry_seconds";


    /**
     * logfile_dir的配置名
     */
    public static final String LOGFILE_DIR = "logfile_dir";
    
    /**
     * max_threads的配置名
     */
    public static final String MAX_THREADS = "max_threads";


    /**
     * 下划线
     */
    public static final String UNDERLINE_STRING = "_";

    /**
     * 文件：
     */
    public static final String STRING_FILE = "ファイル：";

    /**
     * 删除失败
     */
    public static final String DELETE_ERROR = "が削除失敗しました。";

    /**
     * 创建失败
     */
    public static final String CREATE_ERROR = "が作成失敗しました。";

    /**
     * 未找到
     */
    public static final String NOT_FIND_FILE = "が見つかりません。";

    /**
     * 删除处理标志文件
     */
    public static final String MSG_PROC_FLAG_DELETED = "処理中フラグファイルを削除しました。";

    /**
     * 创建一个处理标志文件
     */
    public static final String MSG_PROC_FLAG_CREATED = "処理中フラグファイルを作成しました。";

    /**
     * {0}を開始しました。
     */
    public static final String MESSAGE_I_00001 = "I-00001";

    /**
     * {0}を終了しました。
     */
    public static final String MESSAGE_I_00002 = "I-00002";
    /**
     * {0}が終了していません。
     */
    public static final String MESSAGE_I_00003 = "I-00003";
    /**
     * {0}の登録処理に成功しました。{1}に{2}件登録しました。
     */
    public static final String MESSAGE_I_00004 = "I-00004";


    /**
     * 本プロセスは既に実行中です。処理を終了します。
     */
    public static final String MESSAGE_E_00012 = "E-00012";
    /**
     * {0}の登録処理に失敗しました。対象テーブルは{1}です。メッセージ：{2}
     */
    public static final String MESSAGE_E_00013 = "E-00013";
    /**
     * 下り配信失敗しました（APIGatewayレスポンス：{0}）
     */
    public static final String MESSAGE_W_00037 = "W-00037";


    /**
     * Message.xml配置文件的存放地址
     */
    public static final String MESSAGE_FILE_PATH = "Message.xml";

    /**
     * log文件名称的key值
     */
    public static final String LOG_NAME = "logName";

    /**
     * container中的logger的name
     */
    public static final String LOGGER_CONTAINER = "vmosbatch";

    /**
     * 本系统的logger的name
     */
    public static final String LOGGER_TASK = "task";

    /**
     * 登录地址
     */
    public static final String LOGIN_URL = "api_url_login";

    /**
     * 退出地址
     */
    public static final String LOGOUT_URL = "api_url_logout";

    /**
     * 下发地址
     */
    public static final String FESALESINFOREQ_URL = "api_url_fe_salesinforeq";




    public static final String OK = "認証OK" ;
    public static final String JSON_RETUEN = "returninfodescription" ;
    public static final String MESSAGE = "message" ;
    public static final String RESULT = "result" ;
    public static final String LOGINRESULT = "loginresult" ;
    public static final String TOKEN = "token" ;
    public static final int CODE_200 = 200;
    public static final String CODE_200_STRING = "200";
    public static final String RETRURN_VALUE = "retrurnvalue";
    public static final String TOKEN_RESPONSE_FAILED = "TOKENを取得に失敗しました。レスポンス：";
    public static final String LOGIN_ID="login_id";
    public static final String LOGIN_PASSWORD="login_password";
    /**
     * sql语句的时间格式
     */
    public static final String YMDHIS_DATE_FORMAT = "'%Y/%m/%d %H:%i:%s'";
    public static final String YMDHMS_DATE_FORMAT = "%Y/%m/%d %H:%i:%s.%f";
    public static final String ONE = "1";
    public static final String ZERO = "0";

    public static final String FORMATTER = "%sのデータ";
    public static final String FAULT_JUDGMENT = "マスタボックス故障結果";
    public static final String QUERY_SALESERIA_LMISSED = "欠番";
    public static final String MISSING_NUMBER_CONFIRMATION = "欠番確認";

    public static final String QUERY_SUCCESS = "取得しました。（レコード数：%s）";

    public static final String INSERT_SUCCESS = "作成しました。（レコード数：%s）";

    public static final String UPDATE_SUCCESS = "更新しました。（レコード数：%s）";

    public static final String TRUNCATE_SUCCESS = "削除しました。";

    public static final String QUERY_FAILED = "取得失敗：%s";

    public static final String INSERT_FAILED = "作成失敗：%s";

    public static final String UPDATE_FAILED = "更新失敗：%s";

    public static final String TRUNCATE_FAILED = "削除失敗：%s";
    
    //https
    public static final String HTTP_REQUEST_METHOD_PUT = "PUT";
    public static final String HTTPS_STRING = "https";
    public static final String HTTP_CONTENT_TYPE = "Content-Type";
    public static final String HTTP_X_MS_BLOB_TYPE = "x-ms-blob-type";
    public static final String HTTP_CONTENT_TYPE_STREAM = "application/octet-stream";
    public static final String HEAD_CONTNT_TYPE_JSON = "application/json; charset=utf-8";
    public static final String HEAD_CONTNT_TYPE_CSV = "text/csv";
    public static final String HEAD_CONTNT_TYPE_GZIP = "application/x-gzip";
    public static final String HEAD_BLOB_TYPE = "BlockBlob";
    public static final String HEAD_ACCEPT = "Accept";
    public static final String HEAD_ACCEPT_VALUE = "text/plain;charset=utf-8";
    public static final String HEAD_ACCEPT_TYPE_JSON = "application/json;charset=utf-8";
    public static final String RESPONSE_RESULT_CODE = "ResultCode";
    public static final String RESPONSE_MESSAGE = "Message";
    public static final String RESPONSE_FILEPATH = "FilePath";
    public static final String PARAM_CUSTOMER_CODE = "CustomerCode";
    public static final String PARAM_FILE_NAME = "FileName";
    public static final String HTTP_HEAD_API_KEY= "Ocp-Apim-Subscription-Key";
    public static final String ENCODING_UTF8 = "UTF-8";

    /**
     * 故障获取失败
     */
    public static final String TROUBLE_GET_FAILED="取引通番(通番=1)を取得に失敗しました。";
    /**
     * 故障更新失败
     */
    public static final String TROUBLE_UPDATE_FAILED="最終取引通番テーブルを更新に失敗しました";
    /**
     * 取引通番获取失败
     */
    public static final String SALE_SERIAL_NUMBER_GET_FAILED="取引通番を取得に失敗しました。";
    /**
     * 最終取引通番更新失败
     */
    public static final String END_SALE_SERIAL_NUMBER_UPDATE_FAILED="最終取引通番を更新に失敗しました";
    /**
     * 取引通番欠番情報を追加に失敗
     */
    public static final String SALE_SERIAL_MISSED_ADD_FAILED="取引通番欠番情報を追加に失敗しました。";

    /**
     * 取引通番欠番情報を获取に失敗
     */
    public static final String SALE_SERIAL_MISSED_GET_FAILED="取引通番欠番テーブルから情報を取得に失敗しました。";

    /**
     * 没有取引通番欠番
     */
    public static final String SALE_SERIAL_MISSED_IS_EMPTY="処理待ち取引通番欠番情報がない。";
    /**
     * 登录认证获取token失敗
     */
    public static final String TOKEN_GET_FAILED="ウェブサービスへログインに失敗しました。";
    /**
     * 受信確認処理失敗
     */
    public static final String RECEPTION_CONFIRMATION_PROCESSING_FAILED="受信確認処理に失敗しました。";
    /**
     * 永久欠番処理失敗
     */
    public static final String MISSED_FOREVER_FAILED="永久欠番にする処理に失敗しました。";
    /**
     * 重试次数加一失败
     */
    public static final String RETRY_FREQUENCY_PLUS_FAILED="リトライ回数+1の処理に失敗しました。";
    /**
     *下发失败
     */
    public static final String ISSUED_FAILED="データ通番再送信リクエスト処理に失敗しました。";
    /**
     * 重试次数加一失败
     */
    public static final String RETRY_FREQUENCY_LESS_FAILED="リトライ回数-1の処理に失敗しました。";

    public static final String LOGIN_URL_IS_NULL="LOGIN_URLが設定していない。";

    public static final String LOGOUT_URL_IS_NULL="LOGOUT_URLが設定していない。";

    public static final String FESALESINFOREQ_URL_IS_NULL="FESALESINFOREQ_URLが設定していない。";

    public static final String LOGIN_PASSWORD_IS_NULL="LOGIN_PASSWORDが設定していない。";

    public static final String LOGIN_ID_IS_NULL="LOGIN_IDが設定していない。";

    public static final String RECEPTION_NUMBER_MSG = "受信していた欠番数:";

    public static final String FOREVER_NUMBER_MSG = "永久欠番になる欠番数:";

    public static final String RETRY_NUMBER_MSG = "再要求処理を実施する欠番数: ";

    public static final String SENT_SUCCESS_NUMBER_MSG = "下り送信レスポンスOK数: ";

    public static final String SENT_FAILURE_NUMBER_MSG = "下り送信レスポンスNG数: ";

    public static final String AFTER_REQUEST_RECEPTION_NUMBER_MSG = "再要求処理して受信数: ";

    public static final String AFTER_REQUEST_FOREVER_NUMBER_MSG = "再要求処理して永久欠番数: ";
}

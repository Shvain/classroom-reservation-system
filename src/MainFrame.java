package src;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import	java.util.ArrayList;									// @1
import	java.util.List;											// @1

public class MainFrame extends Frame implements ActionListener, WindowListener{
	ReservationControl	reservationControl;						// ReservationControlクラスのインスタンス生成
	// パネルインスタンスの生成
	Panel	panelNorth;											// 上部パネル
	Panel	panelNorthSub1;										//   @1 上部パネルの上
	Panel	panelNorthSub2;										//   @1 上部パネルの下
	Panel	panelCenter;										// 中央パネル
	Panel	panelSouth;											// @2 下部パネル
	// ボタンインスタンスの生成
	Button	buttonLog;											// ログイン・ログアウトボタン
	Button	buttonExplanation;									// @1 教室概要ボタン
	Button	buttonReservation;
	Button  buttonReservationInformation;									// @2 新規予約ボタン
	// @1 コンボボックスのインスタンス生成
	ChoiceFacility	choiceFacility;								// @1 教室選択用コンボボックス
	// テキストフィールドのインスタンス生成
	TextField	tfLoginID;										// ログインIDを表示するテキストフィールド
	public TextField   tfYear, tfMonth, tfDay;		// 年月日のテキストフィールド
	// テキストエリアのインスタンス生成
	TextArea	textMessage;									// 結果表示用メッセージ欄
	
	//// MainFrameコンストラクタ
	public	MainFrame( ReservationControl rc) {
		reservationControl = new ReservationControl(this);							// ReservationControlインスタンス保存									// ReservationControlインスタンス保存
		// ボタンの生成
		buttonLog = new Button( " ログイン ");					// ログインボタン
		buttonExplanation = new Button( "教室概要");			// @1 教室選択ボタン
		buttonReservation = new Button( "新規予約");			// @2 新規予約ボタン
		buttonReservationInformation = new Button( "予約確認");			// @2 新規予約ボタン
																// @1
		// @1 教室選択用コンボボックスの生成
		List<String> facilityId = new ArrayList<String>();		// @1 全てのfacilityIDを入れるリスト
		facilityId 		= rc.getFacilityId();					// @1 全facilityIDを読出し，リストに入れる
		choiceFacility	= new ChoiceFacility( facilityId);		// @1 教室選択用コンボボックスのインスタンス生成
		// テキストフィールドの生成（年月日）
		tfYear		= new TextField("", 4);
		tfMonth 	= new TextField("", 2);
		tfDay		= new TextField("",2);

		// ログインID用表示ボックスの生成
		tfLoginID = new TextField( "未ログイン", 10);
		tfLoginID.setEditable( false);
		
		// 上中下のパネルを使うため，レイアウトマネージャーにBorderLayoutを設定
		setLayout( new BorderLayout());
		
		// @1 上部パネルの上パネルに「教室予約システム」というラベルと【ログイン】ボタン等を追加
		panelNorthSub1 = new Panel();							// @1 NorthSub1のパネルインスタンスを生成
		panelNorthSub1.add( new Label( "教室予約システム"));	// @1 タイトルラベルを付加
		panelNorthSub1.add( buttonLog);							// @1 ログインボタンを付加
		panelNorthSub1.add( new Label( "ログインID:"));	// @1 ログインIDタイトルラベルを付加
		panelNorthSub1.add( tfLoginID);							// @1 ログインID表示用テキストフィールドを付加
																// @1
		// @1 上部パネルの下パネルに教室選択及び教室概要ボタンを追加							// @1 NorthSub2のパネルインスタンスを生成
		panelNorthSub2 = new Panel();							// @1 NorthSub2のパネルインスタンスを生成
		panelNorthSub2.add( new Label( "教室"));				// @1 教室選択コンボボックスのラベルを付加
		panelNorthSub2.add( choiceFacility);					// @1 教室選択コンボボックスを付加
		panelNorthSub2.add( new Label("予約日"));
		panelNorthSub2.add( tfYear);
		panelNorthSub2.add( new Label("年"));
		panelNorthSub2.add( tfMonth);
		panelNorthSub2.add( new Label("月"));
		panelNorthSub2.add( tfDay);
		panelNorthSub2.add( new Label("日"));
		panelNorthSub2.add( new Label( ""));					// @1 コンボボックスとボタンの隙間をラベルで付加
		panelNorthSub2.add( buttonExplanation);					// @1 教室概要表示ボタンを付加
		panelNorthSub2.add( new Label( ""));				
		panelNorthSub2.add( buttonReservationInformation);		
																// @1
		// @1 上部パネルに上下2つのパネルを追加
		panelNorth = new Panel( new BorderLayout());			// @1 panelNorthをBorderLayoutのパネルで生成
		panelNorth.add( panelNorthSub1, BorderLayout.NORTH);	// @1 panelNorthSub1を上部に付加
		panelNorth.add( panelNorthSub2, BorderLayout.CENTER);	// @1 panelNorthSub2を下部に付加
																// @1
// @1		// 上部パネルに部品を配置
// @1		panelNorth = new Panel();								// 上部パネルインスタンスを生成
// @1		panelNorth.add( new Label( "教室予約システム"));		// タイトルラベルを付加
// @1		panelNorth.add( buttonLog);								// ログインボタンを付加
// @1		panelNorth.add( new Label( "　　　　　　ログインID:"));	// ログインIDラベルを付加
// @1		panelNorth.add( tfLoginID);								// ログインID表示テキストフィールドを付加
		
		// MainFrameに上部パネルを追加
		add( panelNorth, BorderLayout.NORTH);

		// 中央パネルにテキストメッセージ欄を設定
		panelCenter = new Panel();								// 中央パネルインスタンスを生成
		textMessage = new TextArea( 20, 80);					// メッセージ表示用テキストエリアインスタンスを生成
		textMessage.setEditable( false);						// テキストエリアをユーザによる編集不可に設定
		panelCenter.add( textMessage);							// 中央パネルにメッセージ表示エリアを付加
		// MainFrameに中央パネルを追加
		add( panelCenter, BorderLayout.CENTER);
		
		// @2 下部パネルに部品を配置
		panelSouth = new Panel();								// @2下部パネルインスタンスを生成
		panelSouth.add( buttonReservation);						// @2 新規予約ボタンを付加
		// @2 MainFrameに下部パネルを追加
		add( panelSouth, BorderLayout.SOUTH);
		
		// Listenerの追加
		buttonLog.addActionListener( this);						// ActionListenerにログインボタンを追加
		buttonExplanation.addActionListener( this);				// @1 ActionListenerに教室概要ボタンを追加
		buttonReservation.addActionListener( this);				// @2 ActionListenerに新規予約ボタンを追加
		buttonReservationInformation.addActionListener( this);	// @2 ActionListenerに新規予約ボタンを追加
		addWindowListener( this);								// WindowListenerを追加
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		System.exit( 0);
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String	result = new String();
		// 押下ボタンがログインボタンの時，loginLogoutメソッドを実行
		if( e.getSource() == buttonLog) {
			result = reservationControl.loginLogout( this);
		// @1 押下ボタンが教室概要ボタンの時，getFacilityExplanationメソッドを実行
		} else if (e.getSource() == buttonExplanation) {
			String facilityId = choiceFacility.getSelectedItem();
			result = reservationControl.getFacilityExplanation(facilityId);  // 年月日が入力されている場合
		// @2 押下ボタンが新規予約ボタンの時，makeReservationメソッドを実行
		} else if( e.getSource() == buttonReservation) {		// @2
			result = reservationControl.makeReservation( this);	// @2
		// @2 押下ボタンが予約確認ボタンの時，ReservationInformationメソッドを実行
		} else if( e.getSource() == buttonReservationInformation) {		// @2
			result = reservationControl.makeReservationInformation( this);	// @2
		}
		textMessage.setText( result);							// メソッドの戻り値をテキストエリアに表示
	}

}

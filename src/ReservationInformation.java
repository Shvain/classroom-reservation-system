package src;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class ReservationInformation extends Dialog implements ActionListener, WindowListener {
    
    ReservationControl	rc;

    Panel panelNorth;
    Panel panelCenter;
    Panel panelSouth;

    Button buttonReservationCansel;

    TextField	tfLoginID;	
    TextField   tfFacility;

    public ReservationInformation( Frame owner1, ReservationControl rc){
        super( owner1, "予約情報", true);
        this.rc = rc;

        buttonReservationCansel = new Button( "該当の予約をキャンセル");

        panelNorth = new Panel();
        panelCenter = new Panel();
        panelSouth = new Panel();

        // ログインID用表示ボックスの生成
		tfLoginID = new TextField( "未ログイン", 10);
		tfLoginID.setEditable( false);

        panelNorth.add( new Label( "あなたの予約情報"));
        panelNorth.add( new Label( ""));
        panelNorth.add( new Label( "ログインID"));
        panelNorth.add( tfLoginID);

        panelCenter.add( new Label( "予約時間リスト"));
        //ここにデータベースから読み取った予約情報を表示する。

        panelSouth.add( buttonReservationCansel);

        setLayout( new BorderLayout());
		add( panelNorth,	BorderLayout.NORTH);
		add( panelCenter,	BorderLayout.CENTER);
		add( panelSouth,	BorderLayout.SOUTH);
        
        addWindowListener( this);

        buttonReservationCansel.addActionListener( this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // 仮の実装。後で具体的な処理を記述してください。
        System.out.println("Action performed: " + e.getActionCommand());
    }

    @Override
	public void windowOpened(WindowEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		setVisible( false);
		dispose();		
		
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

}

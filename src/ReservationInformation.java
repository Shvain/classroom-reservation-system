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
import java.util.List;


public class ReservationInformation extends Dialog implements ActionListener, WindowListener {
    
    ReservationControl	rc;

    Panel panelNorth;
	Panel panelCenter;
    Panel panelCenterSub1;
	Panel panelCenterSub2;
    Panel panelSouth;

    Button buttonReservationCansel;

    TextField	tfLoginID;	
    TextField   tfFacility;
	TextArea taReservations;

    public ReservationInformation( Frame owner1, ReservationControl rc, String reservationUserID
	, List<ReservationControl.Reservation> reservations){
        super( owner1, "予約情報", true);
        this.rc = rc;

        buttonReservationCansel = new Button( "該当の予約をキャンセル");

        panelNorth = new Panel();
        panelCenter = new Panel();
        panelSouth = new Panel();
		panelCenterSub1 = new Panel();
		panelCenterSub2 = new Panel();

        // ログインID用表示ボックスの生成
		tfLoginID = new TextField( reservationUserID, 10);
		tfLoginID.setEditable( false);

		taReservations = new TextArea(10, 50); // 予約情報を表示するTextArea
        taReservations.setEditable(false);

        panelNorth.add( new Label( "あなたの予約情報"));
        panelNorth.add( new Label( ""));
        panelNorth.add( new Label( "ログインID"));
        panelNorth.add( tfLoginID);

        panelCenterSub1.add( new Label( "予約時間リスト"));
		panelCenterSub2.add( taReservations);

		panelCenter = new Panel( new BorderLayout());			// @1 panelNorthをBorderLayoutのパネルで生成
		panelCenter.add( panelCenterSub1, BorderLayout.NORTH);	// @1 panelNorthSub1を上部に付加
		panelCenter.add( panelCenterSub2, BorderLayout.CENTER);

        //ここにデータベースから読み取った予約情報を表示する。

        panelSouth.add( buttonReservationCansel);

        setLayout( new BorderLayout());
		add( panelNorth,	BorderLayout.NORTH);
		add( panelCenter,	BorderLayout.CENTER);
		add( panelSouth,	BorderLayout.SOUTH);
        
        addWindowListener( this);

        buttonReservationCansel.addActionListener( this);

		if (reservations.isEmpty()) {
            taReservations.setText("予約情報がありません");
        } else {
            StringBuilder sb = new StringBuilder();
            for (ReservationControl.Reservation reservation : reservations) {
                sb.append(reservation.toString()).append("\n");
            }
            taReservations.setText(sb.toString());
        }
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

package src;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.List;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import src.ReservationControl.ReservationActionHandler;


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
	JTable tableReservations; // 予約情報を表示するためのJTable
	Label noReservationsLabel; // データがない場合のメッセージ表示用
	ReservationActionHandler actionHandler;

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

		panelNorth.add( new Label( "あなたの予約情報"));
        panelNorth.add( new Label( ""));
        panelNorth.add( new Label( "ログインID"));
        panelNorth.add( tfLoginID);

		if (reservations.isEmpty()) {
            noReservationsLabel = new Label("予約情報がありません");
            panelCenter.add(noReservationsLabel, BorderLayout.CENTER);
        } else {
            // JTable用のモデルを作成
            String[] columnNames = {"選択", "教室", "予約日", "日程", "開始時間", "終了時間"};
            DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
				@Override
				public Class<?> getColumnClass(int column) {
					switch (column) {
						case 0:
							return Boolean.class;
						default:
							return String.class;
					}
				}
				@Override
				public boolean isCellEditable(int row, int column) {
					return column == 0;
				}
			};
            // 予約情報をモデルに追加
            for (ReservationControl.Reservation reservation : reservations) {
                Object[] row = {
					Boolean.FALSE,
                    reservation.getFacilityId(),
                    reservation.getFormattedDate(),
                    reservation.getFormattedDay(),
                    reservation.getFormattedStartTime(),
                    reservation.getFormattedEndTime()
                };
                model.addRow(row);
            }

            // JTableを作成し、モデルを設定
            tableReservations = new JTable(model);

            // JTableをスクロールペインに追加
            JScrollPane scrollPane = new JScrollPane(tableReservations);
            panelCenter.add(scrollPane, BorderLayout.CENTER);

			actionHandler = rc. new ReservationActionHandler(rc, model);
        }

        panelSouth.add( buttonReservationCansel);

        setLayout( new BorderLayout());
		add( panelNorth,	BorderLayout.NORTH);
		add( panelCenter,	BorderLayout.CENTER);
		add( panelSouth,	BorderLayout.SOUTH);
        
        addWindowListener( this);

        buttonReservationCansel.addActionListener( this);

		// if (reservations.isEmpty()) {
        //     taReservations.setText("予約情報がありません");
        // } else {
        //     StringBuilder sb = new StringBuilder();
        //     for (ReservationControl.Reservation reservation : reservations) {
        //         sb.append(reservation.toString()).append("\n");
        //     }
        //     taReservations.setText(sb.toString());
        // }
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		if("該当の予約をキャンセル".equals(command)){
			actionHandler.handleCancelAction();
		}
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

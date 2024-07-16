package src;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.event.TableModelEvent;
import javax.swing.table.TableColumnModel;

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

    public ReservationInformation( Frame owner ,ReservationControl rc, String reservationUserID
	, List<ReservationControl.Reservation> reservations){
        super( owner, "予約状況", true);
        this.rc = rc;

        buttonReservationCansel = new Button( "該当の予約をキャンセル");

        panelNorth = new Panel();
        panelCenter = new Panel();
        panelSouth = new Panel();

        // ログインID用表示ボックスの生成
		tfLoginID = new TextField( reservationUserID, 10);
		tfLoginID.setEditable( false);

		panelNorth.add( new Label( "あなたの予約状況"));
        panelNorth.add( new Label( ""));
        panelNorth.add( new Label( "ログインID:"));
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
                    reservation.facility_id,
                    reservation.date,
                    reservation.day,
                    reservation.start_time,
                    reservation.end_time
                };
                model.addRow(row);
            }

            // JTableを作成し、モデルを設定
            tableReservations = new JTable(model);

			// カラムのテーブル幅を設定
			TableColumnModel columnModel = tableReservations.getColumnModel();
			columnModel.getColumn(0).setPreferredWidth(50);
			columnModel.getColumn(1).setPreferredWidth(50);
			columnModel.getColumn(2).setPreferredWidth(150);
			columnModel.getColumn(3).setPreferredWidth(150);
			columnModel.getColumn(4).setPreferredWidth(100);
			columnModel.getColumn(5).setPreferredWidth(100);

			// テーブルのサイズを設定
			tableReservations.setPreferredScrollableViewportSize(new Dimension(700,300));

            // JTableをスクロールペインに追加
            JScrollPane scrollPane = new JScrollPane(tableReservations);
			scrollPane.setPreferredSize(new Dimension(700, 300));
            panelCenter.add(scrollPane, BorderLayout.CENTER);

			// テーブルの変更を監視してボタンの有効無効を切り替える
			model.addTableModelListener(new TableModelListener() {
				@Override
				public void tableChanged(TableModelEvent e) {
					updateButtonState();
				}
			});

			actionHandler = rc. new ReservationActionHandler(rc, model);
        }

        panelSouth.add( buttonReservationCansel);

        setLayout( new BorderLayout());
		add( panelNorth,	BorderLayout.NORTH);
		add( panelCenter,	BorderLayout.CENTER);
		add( panelSouth,	BorderLayout.SOUTH);
        
        addWindowListener( this);

        buttonReservationCansel.addActionListener( this);
		updateButtonState();
    }

	private void updateButtonState() {
		if (tableReservations == null) { //updateButtonState メソッドで tableReservations が null かどうかを確認
			buttonReservationCansel.setEnabled(false);
			return;
		}
		boolean isSelected = false;
		for (int i = 0; i < tableReservations.getRowCount(); i++) {
			if ((Boolean) tableReservations.getValueAt(i, 0)) {
				isSelected = true;
				break;
			}
		}
		buttonReservationCansel.setEnabled(isSelected);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		if("該当の予約をキャンセル".equals(command)){
			int response = JOptionPane.showConfirmDialog(
				this,
				"本当に予約をキャンセルしますか？\n※この動作は元に戻すことはできません。",
				"予約キャンセル",
				JOptionPane.YES_NO_OPTION,
				JOptionPane.WARNING_MESSAGE
			);
			if (response == JOptionPane.YES_OPTION) {
				actionHandler.handleCancelAction();
			}
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

package api.busalarm.UserRoute;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import api.busalarm.R;


public class ListAdapter extends BaseAdapter{

    /* 아이템을 세트로 담기 위한 어레이 */
    protected ArrayList<ListNode> mItems = new ArrayList<>();

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Context context = parent.getContext();

        /* 'listview_custom' Layout을 inflate하여 convertView 참조 획득 */
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.user_route_listview, parent, false);
        }

        /* 'listview_custom'에 정의된 위젯에 대한 참조 획득 */
        ImageView icon = (ImageView) convertView.findViewById(R.id.icon) ;
        TextView station = (TextView) convertView.findViewById(R.id.station) ;


        /* 각 리스트에 뿌려줄 아이템을 받아오는데 mMyItem 재활용 */
        ListNode bus = getItem(position);

        /* 각 위젯에 세팅된 아이템을 뿌려준다 */
        icon.setImageDrawable(bus.getIcon());
        station.setText(bus.getStation());

        /* (위젯에 대한 이벤트리스너를 지정하고 싶다면 여기에 작성하면된다..)*/
        return convertView;
    }

    /* 아이템 데이터 추가를 위한 함수. 자신이 원하는대로 작성 */
    public void addList(Drawable icon, String station) {
        ListNode bus = new ListNode();
        bus.setIcon(icon);
        bus.setStation(station);
        mItems.add(bus);
    }
    public void addList(String station) {
        ListNode bus = new ListNode();
        bus.setStation(station);
        mItems.add(bus);
    }

    public void listClear(){
        mItems.clear();
    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public ListNode getItem(int position) {
        return mItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
}
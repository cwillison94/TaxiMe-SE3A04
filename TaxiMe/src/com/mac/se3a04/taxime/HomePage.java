package com.mac.se3a04.taxime;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.mac.se3a04.taxime.R;

public class HomePage extends Fragment implements OnClickListener {
	private Button bRequestCarpool;
	private Button bOfferCarpool;
	private TextView tvNeedHelp;

	public HomePage() {

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_layout_home, container, false);

		setUpWidgets(view);
		return view;
	}

	private void setUpWidgets(View view) {
		bRequestCarpool = (Button) view.findViewById(R.id.bRequestCarpool);
		bRequestCarpool.setOnClickListener(this);
		bOfferCarpool = (Button) view.findViewById(R.id.bOfferCarpool);
		bOfferCarpool.setOnClickListener(this);
		tvNeedHelp = (TextView) view.findViewById(R.id.tvNeedHelp);
		tvNeedHelp.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bRequestCarpool:
			startActivity(new Intent(getActivity(), RequestPage.class));
			Toast.makeText(getActivity(), "Request Carpool", Toast.LENGTH_SHORT).show();
			break;
		case R.id.bOfferCarpool:
			startActivity(new Intent(getActivity(), OfferPage.class));
			Toast.makeText(getActivity(), "Offer Carpool", Toast.LENGTH_SHORT).show();
			break;
		case R.id.tvNeedHelp:
			Fragment fragment = null;
			Bundle args = new Bundle();
			fragment = new FragmentOne();
			args.putString(FragmentOne.ITEM_NAME, TaxiMeMainActivity.dataList.get(6).getItemName());
			args.putInt(FragmentOne.IMAGE_RESOURCE_ID, TaxiMeMainActivity.dataList.get(6)
					.getImgResID());
			fragment.setArguments(args);
			FragmentManager frgManager = getFragmentManager();
			frgManager.beginTransaction().replace(R.id.content_frame, fragment)
					.commit();

			break;
		}

	}

}

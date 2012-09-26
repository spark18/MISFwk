package com.shizhong.web.meta;

import static com.shizhong.web.bizmodel.EntityType.*;

import java.util.HashMap;
import java.util.Map;

import com.shizhong.web.bizmodel.EntityType;

@SuppressWarnings("rawtypes")
public class Command {
	private String name;
	private Map<String, Parameter> parameters = new HashMap<String, Parameter>();
	private static final Map<String, EntityType> entityTypes = new HashMap<String, EntityType>();
	static {
		entityTypes.put(LiGan.name().toLowerCase(), LiGan);
		entityTypes.put(LiGanPreview.name().toLowerCase(), LiGanPreview);
		entityTypes.put(LiGanSZTransaction.name().toLowerCase(), SZTransaction);
		entityTypes.put(TicketSZTransaction.name().toLowerCase(), SZTransaction);
		entityTypes.put(UpLineSZTransaction.name().toLowerCase(), SZTransaction);
		entityTypes.put(DownLineSZTransaction.name().toLowerCase(), SZTransaction);
		entityTypes.put(TingZiSZTransaction.name().toLowerCase(), SZTransaction);
		entityTypes.put(TingZiNanHuiSZTransaction.name().toLowerCase(), SZTransaction);
		entityTypes.put(YangZhaoDianSZTransaction.name().toLowerCase(), SZTransaction);
		entityTypes.put(UpLinePreview.name().toLowerCase(), UpLinePreview);
		entityTypes.put(UpLineView.name().toLowerCase(), UpLineView);
		entityTypes.put(DownLinePreview.name().toLowerCase(), DownLinePreview);
		entityTypes.put(DownLineView.name().toLowerCase(), DownLineView);
		entityTypes.put(BusTicketPreview.name().toLowerCase(), BusTicketPreview);
		entityTypes.put(BusTicketImgPreview.name().toLowerCase(), BusTicketImgPreview);
		entityTypes.put(BusTicketView.name().toLowerCase(), BusTicketView);
		entityTypes.put(BusTicketImgView.name().toLowerCase(), BusTicketImgView);
		entityTypes.put(BusTicketAirPreview.name().toLowerCase(), BusTicketAirPreview);
		entityTypes.put(BusTicketAirImgPreview.name().toLowerCase(), BusTicketAirImgPreview);
		entityTypes.put(BusTicketAirView.name().toLowerCase(), BusTicketAirView);
		entityTypes.put(BusTicketAirImgView.name().toLowerCase(), BusTicketAirImgView);
		entityTypes.put(TingZiPreview.name().toLowerCase(), TingZiPreview);
		entityTypes.put(TingZi.name().toLowerCase(), TingZi);
		entityTypes.put(TingZiNanHuiPreview.name().toLowerCase(), TingZiNanHuiPreview);
		entityTypes.put(TingZiNanHui.name().toLowerCase(), TingZiNanHui);
		entityTypes.put(YangZhaoDian.name().toLowerCase(), YangZhaoDian);
		entityTypes.put(YangZhaoDianPreview.name().toLowerCase(), YangZhaoDianPreview);
	}

	public Command(String path) {
		super();
		this.name = path;
	}
	
	@SuppressWarnings("unchecked")
	public Command(String path, String paramMetas, Map paramValues) {
		super();
		this.name = path;
		String[] paramSignature = paramMetas.split(";");
		for(String param: paramSignature) {
			String[] nameNtype = param.split(":");
			String name = nameNtype[0];
			Class<?> paramType = String.class;
			try {
				paramType = Class.forName("java.lang." + nameNtype[1]);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			Parameter parameter = new Parameter(name, paramValues.get(name), paramType);
			addParameter(parameter);
		}
	}

	public Command(String name, Map<String, Parameter> parameters) {
		super();
		this.name = name;
		this.parameters = parameters;
	}

	public String getName() {
		return name;
	}

	public Map<String, Parameter> getParameters() {
		return parameters;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setParameters(Map<String, Parameter> parameters) {
		this.parameters = parameters;
	}
	
	public void addParameter(Parameter parameter) {
		this.parameters.put(parameter.getName(), parameter);
	}

	public void setPath(String path) {
		this.name = path;
	}

	@Override
	public String toString() {
		return "Command [name=" + name + ", parameters=" + parameters + "]";
	}
	
	public static EntityType entityType(String name) {
		if (null == name) {
			return null;
		}
		return entityTypes.get(name.toLowerCase());
	}

	// Handle all servlet with pattern /xxxshow, case insensitive
	public EntityType entityType() {
		String name = getName().toLowerCase();
		int indexOfHiphon = name.lastIndexOf('-');
		if(indexOfHiphon < 0) {
			return null;
		}
		return entityType(name.substring(0, indexOfHiphon));
	}
}

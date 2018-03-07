package com.example.androidsamplecode;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ResponseHandler {


   

    private String status;
    private ResultBean result;
    private String recordingId;

    public static ResponseHandler objectFromData(String str) {

        return new Gson().fromJson(str, ResponseHandler.class);
    }

    public static List<ResponseHandler> arrayResponseHandlerFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);
            Type listType = new TypeToken<ArrayList<ResponseHandler>>() {
            }.getType();

            return new Gson().fromJson(jsonObject.getString(str), listType);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return new ArrayList();


    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public String getRecordingId() {
        return recordingId;
    }

    public void setRecordingId(String recordingId) {
        this.recordingId = recordingId;
    }

    public static class ResultBean {
     

        private String duration;
        private String sessionStatus;
        private AnalysisSummaryBean analysisSummary;
        private List<AnalysisSegmentsBean> analysisSegments;

        public static ResultBean objectFromData(String str) {

            return new Gson().fromJson(str, ResultBean.class);
        }

        public static List<ResultBean> arrayResultBeanFromData(String str, String key) {

            try {
                JSONObject jsonObject = new JSONObject(str);
                Type listType = new TypeToken<ArrayList<ResultBean>>() {
                }.getType();

                return new Gson().fromJson(jsonObject.getString(str), listType);

            } catch (JSONException e) {
                e.printStackTrace();
            }

            return new ArrayList();


        }

        public String getDuration() {
            return duration;
        }

        public void setDuration(String duration) {
            this.duration = duration;
        }

        public String getSessionStatus() {
            return sessionStatus;
        }

        public void setSessionStatus(String sessionStatus) {
            this.sessionStatus = sessionStatus;
        }

        public AnalysisSummaryBean getAnalysisSummary() {
            return analysisSummary;
        }

        public void setAnalysisSummary(AnalysisSummaryBean analysisSummary) {
            this.analysisSummary = analysisSummary;
        }

        public List<AnalysisSegmentsBean> getAnalysisSegments() {
            return analysisSegments;
        }

        public void setAnalysisSegments(List<AnalysisSegmentsBean> analysisSegments) {
            this.analysisSegments = analysisSegments;
        }

        public static class AnalysisSummaryBean {
          
            private AnalysisResultBean AnalysisResult;

            public static AnalysisSummaryBean objectFromData(String str) {

                return new Gson().fromJson(str, AnalysisSummaryBean.class);
            }

            public static List<AnalysisSummaryBean> arrayAnalysisSummaryBeanFromData(String str, String key) {

                try {
                    JSONObject jsonObject = new JSONObject(str);
                    Type listType = new TypeToken<ArrayList<AnalysisSummaryBean>>() {
                    }.getType();

                    return new Gson().fromJson(jsonObject.getString(str), listType);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return new ArrayList();


            }

            public AnalysisResultBean getAnalysisResult() {
                return AnalysisResult;
            }

            public void setAnalysisResult(AnalysisResultBean AnalysisResult) {
                this.AnalysisResult = AnalysisResult;
            }

            public static class AnalysisResultBean {
            

                private TemperBean Temper;
                private ValenceBean Valence;
                private ArousalBean Arousal;

                public static AnalysisResultBean objectFromData(String str) {

                    return new Gson().fromJson(str, AnalysisResultBean.class);
                }

                public static List<AnalysisResultBean> arrayAnalysisResultBeanFromData(String str, String key) {

                    try {
                        JSONObject jsonObject = new JSONObject(str);
                        Type listType = new TypeToken<ArrayList<AnalysisResultBean>>() {
                        }.getType();

                        return new Gson().fromJson(jsonObject.getString(str), listType);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    return new ArrayList();


                }

                public TemperBean getTemper() {
                    return Temper;
                }

                public void setTemper(TemperBean Temper) {
                    this.Temper = Temper;
                }

                public ValenceBean getValence() {
                    return Valence;
                }

                public void setValence(ValenceBean Valence) {
                    this.Valence = Valence;
                }

                public ArousalBean getArousal() {
                    return Arousal;
                }

                public void setArousal(ArousalBean Arousal) {
                    this.Arousal = Arousal;
                }

                public static class TemperBean {
                

                    private String Mode;
                    private String ModePct;

                    public static TemperBean objectFromData(String str) {

                        return new Gson().fromJson(str, TemperBean.class);
                    }

                    public static List<TemperBean> arrayTemperBeanFromData(String str, String key) {

                        try {
                            JSONObject jsonObject = new JSONObject(str);
                            Type listType = new TypeToken<ArrayList<TemperBean>>() {
                            }.getType();

                            return new Gson().fromJson(jsonObject.getString(str), listType);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        return new ArrayList();


                    }

                    public String getMode() {
                        return Mode;
                    }

                    public void setMode(String Mode) {
                        this.Mode = Mode;
                    }

                    public String getModePct() {
                        return ModePct;
                    }

                    public void setModePct(String ModePct) {
                        this.ModePct = ModePct;
                    }
                }

                public static class ValenceBean {
             
                    private String Mode;
                    private String ModePct;

                    public static ValenceBean objectFromData(String str) {

                        return new Gson().fromJson(str, ValenceBean.class);
                    }

                    public static List<ValenceBean> arrayValenceBeanFromData(String str, String key) {

                        try {
                            JSONObject jsonObject = new JSONObject(str);
                            Type listType = new TypeToken<ArrayList<ValenceBean>>() {
                            }.getType();

                            return new Gson().fromJson(jsonObject.getString(str), listType);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        return new ArrayList();


                    }

                    public String getMode() {
                        return Mode;
                    }

                    public void setMode(String Mode) {
                        this.Mode = Mode;
                    }

                    public String getModePct() {
                        return ModePct;
                    }

                    public void setModePct(String ModePct) {
                        this.ModePct = ModePct;
                    }
                }

                public static class ArousalBean {
                 
                    private String Mode;
                    private String ModePct;

                    public static ArousalBean objectFromData(String str) {

                        return new Gson().fromJson(str, ArousalBean.class);
                    }

                    public static List<ArousalBean> arrayArousalBeanFromData(String str, String key) {

                        try {
                            JSONObject jsonObject = new JSONObject(str);
                            Type listType = new TypeToken<ArrayList<ArousalBean>>() {
                            }.getType();

                            return new Gson().fromJson(jsonObject.getString(str), listType);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        return new ArrayList();


                    }

                    public String getMode() {
                        return Mode;
                    }

                    public void setMode(String Mode) {
                        this.Mode = Mode;
                    }

                    public String getModePct() {
                        return ModePct;
                    }

                    public void setModePct(String ModePct) {
                        this.ModePct = ModePct;
                    }
                }
            }
        }

        public static class AnalysisSegmentsBean {
      

            private int offset;
            private int duration;
            private int end;
            private AnalysisBean analysis;

            public static AnalysisSegmentsBean objectFromData(String str) {

                return new Gson().fromJson(str, AnalysisSegmentsBean.class);
            }

            public static List<AnalysisSegmentsBean> arrayAnalysisSegmentsBeanFromData(String str, String key) {

                try {
                    JSONObject jsonObject = new JSONObject(str);
                    Type listType = new TypeToken<ArrayList<AnalysisSegmentsBean>>() {
                    }.getType();

                    return new Gson().fromJson(jsonObject.getString(str), listType);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return new ArrayList();


            }

            public int getOffset() {
                return offset;
            }

            public void setOffset(int offset) {
                this.offset = offset;
            }

            public int getDuration() {
                return duration;
            }

            public void setDuration(int duration) {
                this.duration = duration;
            }

            public int getEnd() {
                return end;
            }

            public void setEnd(int end) {
                this.end = end;
            }

            public AnalysisBean getAnalysis() {
                return analysis;
            }

            public void setAnalysis(AnalysisBean analysis) {
                this.analysis = analysis;
            }

            public static class AnalysisBean {
              
                private ArousalBeanX Arousal;

                public static AnalysisBean objectFromData(String str) {

                    return new Gson().fromJson(str, AnalysisBean.class);
                }

                public static List<AnalysisBean> arrayAnalysisBeanFromData(String str, String key) {

                    try {
                        JSONObject jsonObject = new JSONObject(str);
                        Type listType = new TypeToken<ArrayList<AnalysisBean>>() {
                        }.getType();

                        return new Gson().fromJson(jsonObject.getString(str), listType);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    return new ArrayList();


                }

                public ArousalBeanX getArousal() {
                    return Arousal;
                }

                public void setArousal(ArousalBeanX Arousal) {
                    this.Arousal = Arousal;
                }

                public static class ArousalBeanX {
          
                    private String Value;
                    private String Group;

                    public static ArousalBeanX objectFromData(String str) {

                        return new Gson().fromJson(str, ArousalBeanX.class);
                    }

                    public static List<ArousalBeanX> arrayArousalBeanXFromData(String str, String key) {

                        try {
                            JSONObject jsonObject = new JSONObject(str);
                            Type listType = new TypeToken<ArrayList<ArousalBeanX>>() {
                            }.getType();

                            return new Gson().fromJson(jsonObject.getString(str), listType);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        return new ArrayList();


                    }

                    public String getValue() {
                        return Value;
                    }

                    public void setValue(String Value) {
                        this.Value = Value;
                    }

                    public String getGroup() {
                        return Group;
                    }

                    public void setGroup(String Group) {
                        this.Group = Group;
                    }
                }
            }
        }
    }
}

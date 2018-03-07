package com.example.androidsamplecode;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;



public class Resp {

    private String status;
    private ResultBean result;

    public static Resp objectFromData(String str) {

        return new Gson().fromJson(str, Resp.class);
    }

    public static Resp objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), Resp.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<Resp> arrayRespFromData(String str) {

        Type listType = new TypeToken<ArrayList<Resp>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public static List<Resp> arrayRespFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);
            Type listType = new TypeToken<ArrayList<Resp>>() {
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

    public static class ResultBean {
     
        private String duration;
        private String sessionStatus;
        private List<AnalysisSegmentsBean> analysisSegments;

        public static ResultBean objectFromData(String str) {

            return new Gson().fromJson(str, ResultBean.class);
        }

        public static ResultBean objectFromData(String str, String key) {

            try {
                JSONObject jsonObject = new JSONObject(str);

                return new Gson().fromJson(jsonObject.getString(str), ResultBean.class);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        public static List<ResultBean> arrayResultBeanFromData(String str) {

            Type listType = new TypeToken<ArrayList<ResultBean>>() {
            }.getType();

            return new Gson().fromJson(str, listType);
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

        public List<AnalysisSegmentsBean> getAnalysisSegments() {
            return analysisSegments;
        }

        public void setAnalysisSegments(List<AnalysisSegmentsBean> analysisSegments) {
            this.analysisSegments = analysisSegments;
        }

        public static class AnalysisSegmentsBean {


            private int offset;
            private int duration;
            private int end;
            private AnalysisBean analysis;

            public static AnalysisSegmentsBean objectFromData(String str) {

                return new Gson().fromJson(str, AnalysisSegmentsBean.class);
            }

            public static AnalysisSegmentsBean objectFromData(String str, String key) {

                try {
                    JSONObject jsonObject = new JSONObject(str);

                    return new Gson().fromJson(jsonObject.getString(str), AnalysisSegmentsBean.class);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return null;
            }

            public static List<AnalysisSegmentsBean> arrayAnalysisSegmentsBeanFromData(String str) {

                Type listType = new TypeToken<ArrayList<AnalysisSegmentsBean>>() {
                }.getType();

                return new Gson().fromJson(str, listType);
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

                private ArousalBean Arousal;

                public static AnalysisBean objectFromData(String str) {

                    return new Gson().fromJson(str, AnalysisBean.class);
                }

                public static AnalysisBean objectFromData(String str, String key) {

                    try {
                        JSONObject jsonObject = new JSONObject(str);

                        return new Gson().fromJson(jsonObject.getString(str), AnalysisBean.class);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    return null;
                }

                public static List<AnalysisBean> arrayAnalysisBeanFromData(String str) {

                    Type listType = new TypeToken<ArrayList<AnalysisBean>>() {
                    }.getType();

                    return new Gson().fromJson(str, listType);
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

                public ArousalBean getArousal() {
                    return Arousal;
                }

                public void setArousal(ArousalBean Arousal) {
                    this.Arousal = Arousal;
                }

                public static class ArousalBean {
                    

                    private String Value;
                    private String Group;

                    public static ArousalBean objectFromData(String str) {

                        return new Gson().fromJson(str, ArousalBean.class);
                    }

                    public static ArousalBean objectFromData(String str, String key) {

                        try {
                            JSONObject jsonObject = new JSONObject(str);

                            return new Gson().fromJson(jsonObject.getString(str), ArousalBean.class);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        return null;
                    }

                    public static List<ArousalBean> arrayArousalBeanFromData(String str) {

                        Type listType = new TypeToken<ArrayList<ArousalBean>>() {
                        }.getType();

                        return new Gson().fromJson(str, listType);
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

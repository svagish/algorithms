class Solution {
    public int countDaysTogether(String arriveAlice, String leaveAlice, String arriveBob, String leaveBob) {
        int[] month = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        String[] arriveAliceArr = arriveAlice.split("-");
        int arriveAliceMonth = Integer.valueOf(arriveAliceArr[0]);
        int arriveAliceDay = Integer.valueOf(arriveAliceArr[1]);

        String[] leaveAliceArr = leaveAlice.split("-");
        int leaveAliceMonth = Integer.valueOf(leaveAliceArr[0]);
        int leaveAliceDay = Integer.valueOf(leaveAliceArr[1]);

        String[] arriveBobArr = arriveBob.split("-");
        int arriveBobMonth = Integer.valueOf(arriveBobArr[0]);
        int arriveBobDay = Integer.valueOf(arriveBobArr[1]);
        
        String[] leaveBobArr = leaveBob.split("-");
        int leaveBobMonth = Integer.valueOf(leaveBobArr[0]);
        int leaveBobDay = Integer.valueOf(leaveBobArr[1]);

        // late arrival month and day
        int lateArriveMonth = 0;
        int lateArriveDay = 0;
        if (arriveAliceMonth > arriveBobMonth) {
            lateArriveMonth = arriveAliceMonth;
            lateArriveDay = arriveAliceDay;
        }
        else if (arriveAliceMonth == arriveBobMonth) {
            lateArriveMonth = arriveAliceMonth;
            lateArriveDay = Math.max(arriveAliceDay, arriveBobDay);
        }
        else {
            lateArriveMonth = arriveBobMonth;
            lateArriveDay = arriveBobDay;
        }

        // early leave month and day
        int earlyLeaveMonth = 0;
        int earlyLeaveDay = 0;
        if (leaveAliceMonth < leaveBobMonth) {
            earlyLeaveMonth = leaveAliceMonth;
            earlyLeaveDay = leaveAliceDay;
        }
        else if (leaveAliceMonth == leaveBobMonth) {
            earlyLeaveMonth = leaveAliceMonth;
            earlyLeaveDay = Math.min(leaveAliceDay, leaveBobDay);
        }
        else {
            earlyLeaveMonth = leaveBobMonth;
            earlyLeaveDay = leaveBobDay;
        }

        if (lateArriveMonth > earlyLeaveMonth) return 0;
        else if (lateArriveMonth == earlyLeaveMonth) return lateArriveDay > earlyLeaveDay ? 0 : earlyLeaveDay-lateArriveDay+1; 

        // System.out.println("late arrival: " + lateArriveMonth +", " + lateArriveDay);
        // System.out.println("early leave: " + earlyLeaveMonth +", " + earlyLeaveDay);

        int ans = 0;
        for (int m = lateArriveMonth; m <= earlyLeaveMonth; m++) {
            if (m == lateArriveMonth) ans += month[m]-lateArriveDay+1;
            else if (m == earlyLeaveMonth) ans += earlyLeaveDay; 
            else ans += month[m];
        }

        return ans;
    }
}
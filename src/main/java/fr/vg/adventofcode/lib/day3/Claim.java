package fr.vg.adventofcode.lib.day3;

public class Claim {

    public static final int FABRIC_LENGTH = 1000;

    public final String initialReprensentation;

    public long id;

    public long offsetLeft;

    public long offsetTop;

    public long width;

    public long height;

    private long[][] values;

    public Claim(String claimRepresentation) {
        // claimRepresentation = #123 @ 3,2: 5x4
        String[] claimsInfo = claimRepresentation.split(" ");
        this.initialReprensentation = claimRepresentation;
        this.id = Long.valueOf(claimsInfo[0].substring(1));
        this.offsetLeft = Long.valueOf(claimsInfo[2].split(",")[0]);
        this.offsetTop = Long.valueOf(claimsInfo[2].split(",")[1].substring(0, claimsInfo[2].split(",")[1].length() - 1));
        this.width = Long.valueOf(claimsInfo[3].split("x")[0]);
        this.height = Long.valueOf(claimsInfo[3].split("x")[1]);
        this.initValues();
    }

    private void initValues() {
        this.values = new long[FABRIC_LENGTH][];
        for (int i = 0; i < FABRIC_LENGTH; i++) {
            this.values[i] = new long[FABRIC_LENGTH];
            for (int j = 0; j < FABRIC_LENGTH; j++) {
                this.values[i][j] = getOccupationRepresentation(i, j);
            }
        }
    }

    private long getOccupationRepresentation(long x, long y) {
        if (y >= this.offsetLeft && y < (this.offsetLeft + this.width )) {
            if (x >= this.offsetTop && x < this.offsetTop + this.height) {
                return 1l;
            }
        }
        return 0l;
    }

    @Override
    public String toString() {
        StringBuilder sb =  new StringBuilder();
        for (int i = 0; i < FABRIC_LENGTH; i++) {
            for (int j = 0; j < FABRIC_LENGTH; j++) {
                 sb.append(this.values[i][j]);
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public Claim clone() {
        return new Claim(this.initialReprensentation);
    }

    public Claim merge(Claim merging) {
        for (int i = 0; i < FABRIC_LENGTH; i++) {
            for (int j = 0; j < FABRIC_LENGTH; j++) {
                this.values[i][j] += merging.values[i][j];
            }
        }
        return this;
    }
}

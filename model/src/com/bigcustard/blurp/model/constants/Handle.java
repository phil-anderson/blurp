package com.bigcustard.blurp.model.constants;

public enum Handle {

    TopLeft(VHandle.Top, HHandle.Left),
    Top(VHandle.Top, HHandle.Center),
    TopRight(VHandle.Top, HHandle.Right),
    Left(VHandle.Middle, HHandle.Left),
    Center(VHandle.Middle, HHandle.Center),
    Right(VHandle.Middle, HHandle.Right),
    BottomLeft(VHandle.Bottom, HHandle.Left),
    Bottom(VHandle.Bottom, HHandle.Center),
    BottomRight(VHandle.Bottom, HHandle.Right);

    public enum HHandle { Left, Center, Right }
    public enum VHandle { Top, Middle, Bottom }

    private final HHandle hHandle;
    private final VHandle vHandle;

    private Handle(VHandle vHandle, HHandle hHandle) {

        this.hHandle = hHandle;
        this.vHandle = vHandle;
    }

    public HHandle gethHandle() {

        return hHandle;
    }

    public VHandle getvHandle() {

        return vHandle;
    }
}




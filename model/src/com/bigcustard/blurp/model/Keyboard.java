package com.bigcustard.blurp.model;

/**
 * Allows you to get the state of the keyboad - assuming the device your program is running on has a keyboard.
 */
public abstract class Keyboard {

    public final Key F1 = new Key(244);
    public final Key F2 = new Key(245);
    public final Key F3 = new Key(246);
    public final Key F4 = new Key(247);
    public final Key F5 = new Key(248);
    public final Key F6 = new Key(249);
    public final Key F7 = new Key(250);
    public final Key F8 = new Key(251);
    public final Key F9 = new Key(252);
    public final Key F10 = new Key(253);
    public final Key F11 = new Key(254);
    public final Key F12 = new Key(255);

    public final Key Num0 = new Key(7);
    public final Key Num1 = new Key(8);
    public final Key Num2 = new Key(9);
    public final Key Num3 = new Key(10);
    public final Key Num4 = new Key(11);
    public final Key Num5 = new Key(12);
    public final Key Num6 = new Key(13);
    public final Key Num7 = new Key(14);
    public final Key Num8 = new Key(15);
    public final Key Num9 = new Key(16);

    public final Key A = new Key(29);
    public final Key B = new Key(30);
    public final Key C = new Key(31);
    public final Key D = new Key(32);
    public final Key E = new Key(33);
    public final Key F = new Key(34);
    public final Key G = new Key(35);
    public final Key H = new Key(36);
    public final Key I = new Key(37);
    public final Key J = new Key(38);
    public final Key K = new Key(39);
    public final Key L = new Key(40);
    public final Key M = new Key(41);
    public final Key N = new Key(42);
    public final Key O = new Key(43);
    public final Key P = new Key(44);
    public final Key Q = new Key(45);
    public final Key R = new Key(46);
    public final Key S = new Key(47);
    public final Key T = new Key(48);
    public final Key U = new Key(49);
    public final Key V = new Key(50);
    public final Key W = new Key(51);
    public final Key X = new Key(52);
    public final Key Y = new Key(53);
    public final Key Z = new Key(54);

    public final Key Up = new Key(19);
    public final Key Down = new Key(20);
    public final Key Left = new Key(21);
    public final Key Right = new Key(22);

    public final Key LeftAlt = new Key(57);
    public final Key LeftControl = new Key(129);
    public final Key LeftShift = new Key(59);
    public final Key RightAlt = new Key(58);
    public final Key RightControl = new Key(130);
    public final Key RightShift = new Key(60);

    public final Key Apostrophe = new Key(75);
    public final Key At = new Key(77);
    public final Key Back = new Key(4);
    public final Key Backslash = new Key(73);
    public final Key Backspace = new Key(67);
    public final Key Colon = new Key(243);
    public final Key Comma = new Key(55);
    public final Key Del = new Key(67);
    public final Key End = new Key(132);
    public final Key Enter = new Key(66);
    public final Key Equals = new Key(70);
    public final Key Escape = new Key(131);
    public final Key ForwardDel = new Key(112);
    public final Key Grave = new Key(68);
    public final Key Hash = new Key(18);
    public final Key Home = new Key(3);
    public final Key Insert = new Key(133);
    public final Key LeftBracket = new Key(71);
    public final Key Minus = new Key(69);
    public final Key PageDown = new Key(93);
    public final Key PageUp = new Key(92);
    public final Key Period = new Key(56);
    public final Key Plus = new Key(81);
    public final Key Pound = new Key(18);
    public final Key RightBracket = new Key(72);
    public final Key Semicolon = new Key(74);
    public final Key Slash = new Key(76);
    public final Key Space = new Key(62);
    public final Key Star = new Key(17);
    public final Key Sym = new Key(63);
    public final Key Tab = new Key(61);

    public final Key NumLock = new Key(78);
    public final Key NumPad0 = new Key(144);
    public final Key NumPad1 = new Key(145);
    public final Key NumPad2 = new Key(146);
    public final Key NumPad3 = new Key(147);
    public final Key NumPad4 = new Key(148);
    public final Key NumPad5 = new Key(149);
    public final Key NumPad6 = new Key(150);
    public final Key NumPad7 = new Key(151);
    public final Key NumPad8 = new Key(152);
    public final Key NumPad9 = new Key(153);

    public final Key MetaAltLeftOn = new Key(16);
    public final Key MetaAltOn = new Key(2);
    public final Key MetaAltRightOn = new Key(32);
    public final Key MetaShiftLeftOn = new Key(64);
    public final Key MetaShiftOn = new Key(1);
    public final Key MetaShiftRightOn = new Key(128);
    public final Key MetaSymOn = new Key(4);

    public final Key Call = new Key(5);
    public final Key Camera = new Key(27);
    public final Key Center = new Key(23);
    public final Key Clear = new Key(28);
    public final Key EndCall = new Key(6);
    public final Key Envelope = new Key(65);
    public final Key Explorer = new Key(64);
    public final Key Focus = new Key(80);
    public final Key HeadsetHook = new Key(79);
    public final Key MediaFastForward = new Key(90);
    public final Key MediaNext = new Key(87);
    public final Key MediaPlayPause = new Key(85);
    public final Key MediaPrevious = new Key(88);
    public final Key MediaRewind = new Key(89);
    public final Key MediaStop = new Key(86);
    public final Key Menu = new Key(82);
    public final Key Mute = new Key(91);
    public final Key Notification = new Key(83);
    public final Key PictSymbols = new Key(94);
    public final Key Power = new Key(26);
    public final Key Search = new Key(84);
    public final Key SoftLeft = new Key(1);
    public final Key SoftRight = new Key(2);
    public final Key SwitchCharset = new Key(95);
    public final Key VolumeDown = new Key(25);
    public final Key VolumeUp = new Key(24);

    public final Key Dpad_Center = new Key(23);
    public final Key Dpad_Down = new Key(20);
    public final Key Dpad_Left = new Key(21);
    public final Key Dpad_Right = new Key(22);
    public final Key Dpad_Up = new Key(19);

    public final Key Button_A = new Key(96);
    public final Key Button_B = new Key(97);
    public final Key Button_C = new Key(98);
    public final Key Button_Circle = new Key(255);
    public final Key Button_L1 = new Key(102);
    public final Key Button_L2 = new Key(104);
    public final Key Button_Mode = new Key(110);
    public final Key Button_R1 = new Key(103);
    public final Key Button_R2 = new Key(105);
    public final Key Button_Select = new Key(109);
    public final Key Button_Start = new Key(108);
    public final Key Button_ThumbL = new Key(106);
    public final Key Button_ThumbR = new Key(107);
    public final Key Button_X = new Key(99);
    public final Key Button_Y = new Key(100);
    public final Key Button_Z = new Key(101);

    public final Key Unknown = new Key(0);

    public char typedCharacter = 0;

    public boolean wasKeyTyped() {

        return typedCharacter != 0;
    }

    public class Key {

        public final int keyCode;

        private Key(int keyCode) {

            this.keyCode = keyCode;
        }

        public boolean isPressed() {

            return isKeyPressed(this);
        }

        public boolean wasJustPressed() {

            return wasKeyJustPressed(this);
        }

        public boolean wasJustReleased() {

            return wasKeyJustReleased(this);
        }

        public Key onPressed(Runnable action) {

            onKeyPressed(this, action);
            return this;
        }

        public Key onReleased(Runnable action) {

            onKeyReleased(this, action);
            return this;
        }
    }

    protected abstract boolean isKeyPressed(Key key);

    protected abstract boolean wasKeyJustPressed(Key key);

    protected abstract boolean wasKeyJustReleased(Key key);

    protected abstract Keyboard onKeyPressed(Key key, Runnable action);

    protected abstract Keyboard onKeyReleased(Key key, Runnable action);

}

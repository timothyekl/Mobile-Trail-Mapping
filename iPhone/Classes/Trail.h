#import <Foundation/Foundation.h>
#import <UIKit/UIKit.h>

@interface Trail : NSObject {

    NSString *_name;
    NSMutableArray *_trailPoints;
    NSMutableArray *_trailHeads;
    UIColor *_trailPaint;
}

@property (nonatomic, retain) NSString *name;
@property (nonatomic, retain) NSMutableArray *trailPoints;
@property (nonatomic, retain) NSMutableArray *trailHeads;
@property (nonatomic, retain) UIColor *trailPaint;

@end
